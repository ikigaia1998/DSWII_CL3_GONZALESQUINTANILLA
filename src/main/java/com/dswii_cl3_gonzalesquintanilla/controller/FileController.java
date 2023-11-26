    package com.dswii_cl3_gonzalesquintanilla.controller;

    import lombok.AllArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.multipart.MultipartFile;
    import com.dswii_cl3_gonzalesquintanilla.model.response.ResponseFile;
    import com.dswii_cl3_gonzalesquintanilla.service.FileService;

    import java.util.List;

    @AllArgsConstructor
    @RestController
    @RequestMapping("api/v1/file")
    public class FileController {
        private FileService fileService;
        @PostMapping("/upload")
        public ResponseEntity<ResponseFile> subirArchivos(@RequestParam("files") List<MultipartFile> files) throws Exception {
            // Validar la extensión de los archivos
            for (MultipartFile file : files) {
                if (!file.getOriginalFilename().toLowerCase().endsWith(".png")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(ResponseFile.builder().message("Solo se permiten archivos PNG").build());
                }
            }
            fileService.guardarArchivos(files);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseFile.builder().message("Los imágenes fueron cargados correctamente").build());
        }

        @PostMapping("/upload/excel")
        public ResponseEntity<ResponseFile> subirArchivosExcel(@RequestParam("files") List<MultipartFile> files) throws Exception {
            // Validar la extensión y el tamaño de los archivos
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                if (!fileName.toLowerCase().endsWith(".xlsx")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(ResponseFile.builder().message("Solo se permiten archivos XLSX").build());
                }

                if (file.getSize() > 1.5 * 1024 * 1024) { // 1.5MB en bytes
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(ResponseFile.builder().message("El tamaño máximo del archivo es 1.5MB").build());
                }
            }

            // Si todos los archivos cumplen con las condiciones, guardarlos
            fileService.guardarArchivos(files);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseFile.builder().message("Los archivos Excel fueron cargados correctamente").build());
        }
    }
