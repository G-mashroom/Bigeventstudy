package org.spstudy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.spstudy.pojo.Result;

@RestController
public class FileUploadController {
//    @PostMapping("/upload")
//    public Result<String> upload(MultipartFile file) throws Exception {
//        String originalFilename= file.getOriginalFilename();
//        String filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
////        file.transferTo(new File("/Users/gz/files/" + filename));
//        String url = AliOssUtil.uploadFile(filename, file.getInputStream());
//        return Result.success(url);
//     }

//    @PostMapping("/upload")
//    public Result<String> upload(MultipartFile file) throws Exception {
//        String originalFilename= file.getOriginalFilename();
//        String filename = originalFilename;
////        String filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
//    //        file.transferTo(new File("/Users/gz/files/" + filename));
//        String url = AliOssUtil.uploadFile(filename, file.getInputStream());
//        return Result.success();
//    }
//    @PostMapping("/uploadFolder")
//    public Result<String> uploadFolder(@RequestParam("folderPath") String folderPath) {
//        // 指定上传到OSS的文件夹前缀 可以不存在 此处命名
//        String ossFolderPrefix = "gz/";
//
//        try {
//            AliOssUtil.uploadFolder(folderPath, ossFolderPrefix);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.error("上传失败");
//        }
//
//        return Result.success("上传成功");
//    }

}

