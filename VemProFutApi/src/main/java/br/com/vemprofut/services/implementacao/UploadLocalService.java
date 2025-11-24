package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.services.IUploadLocalService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadLocalService implements IUploadLocalService {

  @Value("${app.upload.dir}")
  private String uploadDir;

  @Override
  public String upload(MultipartFile file, String folder) throws IOException {

    // Cria o caminho completo: uploads/peladeiros ou uploads/futs
    Path uploadPath = Paths.get(uploadDir, folder);

    // Cria a pasta se não existir
    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }

    // Nome único para evitar sobrescrições
    String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
    Path filePath = uploadPath.resolve(fileName);

    // Salva o arquivo
    Files.copy(file.getInputStream(), filePath);

    // Retorna URL acessável
    return "/uploads/" + folder + "/" + fileName;
  }
}
