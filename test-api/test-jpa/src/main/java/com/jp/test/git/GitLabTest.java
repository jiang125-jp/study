package com.jp.test.git;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jp.test.git.model.ProjectVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
public class GitLabTest {
    private RestTemplate restTemplate;
    private HttpEntity<String> requestEntity;
    private static final String PRIVATE_TOKEN = "Y6nVp_jR2G9MRbdjDw1o";

    public GitLabTest() {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("PRIVATE-TOKEN", PRIVATE_TOKEN);
        headers.add("User-Agent", "Chrome/69.0.3497.81 Safari/537.36");
        requestEntity = new HttpEntity<>(null, headers);
    }

    @GetMapping("/projects")
    public List<ProjectVo> getToken() {
        List<ProjectVo> voList = new ArrayList<>();
        //获取用户project列表
        String reqUserTokenUrl = "https://gitlab.com/api/v4/projects?owned=true";
        ResponseEntity<String> result = restTemplate.exchange(reqUserTokenUrl, HttpMethod.GET, requestEntity, String.class);
        if (StringUtils.isNotEmpty(result.getBody())) {
            JSONArray objects = JSON.parseArray(result.getBody());
            if (objects != null) {
                for (Object o : objects) {
                    JSONObject jsonObject = JSON.parseObject(o.toString());
                    ProjectVo vo = new ProjectVo();
                    vo.setId(jsonObject.getInteger("id"));
                    vo.setName(jsonObject.getString("name"));
                    voList.add(vo);
                }
            }
        }
        return voList;
    }

    @GetMapping("/project/{id}")
    public ProjectVo getToken(@PathVariable Integer id) {
        ProjectVo vo = new ProjectVo();
        //获取project
        String reqUserTokenUrl = "https://gitlab.com/api/v4/projects/" + id;
        ResponseEntity<String> result = restTemplate.exchange(reqUserTokenUrl, HttpMethod.GET, requestEntity, String.class);
        if (StringUtils.isNotEmpty(result.getBody())) {
            JSONObject jsonObject = JSON.parseObject(result.getBody());
            vo.setId(jsonObject.getInteger("id"));
            vo.setName(jsonObject.getString("name"));
            vo.setNameWithNamespace(jsonObject.getString("name_with_namespace"));
            vo.setPathWithNamespace(jsonObject.getString("path_with_namespace"));
            vo.setWebUrl(jsonObject.getString("web_url"));
            vo.setCreatedAt(jsonObject.getString("created_at"));
        }
        return vo;
    }

    @PostMapping("/project/{id}/check-out")
    public void checkOut(@PathVariable Integer id) throws Exception {
        //根据api下载master分支代码压缩文件
        String checkOutUrl = "https://gitlab.com/api/v4/projects/" + id + "/repository/archive.zip";
        ResponseEntity<byte[]> response = restTemplate.exchange(checkOutUrl, HttpMethod.GET, requestEntity, byte[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            //获取byte压缩文件
            byte[] result = response.getBody();
            if (result != null) {
                //创建存放目录
                String targetPath = "D:\\gitDemo\\";
                if (!Files.exists(Paths.get(targetPath))) {
                    Files.createDirectories(Paths.get(targetPath));
                }
                //保存压缩文件
                File file = new File(id + ".zip");
                OutputStream outputStream = new FileOutputStream(targetPath + File.separator + file);
                InputStream inputStream = new ByteArrayInputStream(result);
                byte[] buff = new byte[1024];
                int len;
                while ((len = inputStream.read(buff)) != -1) {
                    outputStream.write(buff, 0, len);
                }
                //关闭流
                inputStream.close();
                outputStream.close();
                System.out.println("下载完成!");
                //解压
                File zipFile = new File(targetPath + File.separator + file);
                if (!zipFile.exists()) {
                    System.out.println("压缩文件不存在!");
                }
                ZipInputStream zipInputStreamIn = new ZipInputStream(new FileInputStream(zipFile));
                ZipEntry zipEntry;
                File f;
                while ((zipEntry = zipInputStreamIn.getNextEntry()) != null) {
                    if (!zipEntry.isDirectory()) {
                        f = new File(targetPath, zipEntry.getName());
                        if (!f.exists()) {
                            //创建此文件的上级目录
                            Files.createDirectories(Paths.get(f.getParent()));
                        }
                        OutputStream out = new FileOutputStream(f);
                        BufferedOutputStream bos = new BufferedOutputStream(out);
                        int length;

                        byte[] buf = new byte[1024];
                        while ((length = zipInputStreamIn.read(buf)) != -1) {
                            bos.write(buf, 0, length);
                        }
                        // 关流顺序，先打开的后关闭
                        bos.close();
                        out.close();
                    }
                }
                //关闭解压流
                zipInputStreamIn.close();
                //释放资源
                System.gc();
                //删除压缩文件
                if (zipFile.delete()) {
                    System.out.println("解压成功!");
                }
            }
        }
    }
}
