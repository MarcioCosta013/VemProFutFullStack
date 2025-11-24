package br.com.vemprofut.services;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadLocalService {

  String upload(MultipartFile file, String folder) throws IOException;
}
