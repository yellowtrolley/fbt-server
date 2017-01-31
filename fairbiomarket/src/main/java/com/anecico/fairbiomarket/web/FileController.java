package com.anecico.fairbiomarket.web;

import com.anecico.fairbiomarket.domain.repo.FileRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * Created by guerrpa on 23/01/2017.
 */
@RestController
@RequestMapping("files")
public class FileController {
    @Autowired
    private FileRepository fileRepository;
    @Value("${profile.image.max.width}") private Integer imageMaxWidth;
    @Value("${profile.image.max.size}") private Double imageMaxSize;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.POST)
    public List<String> handleImageUpload(@RequestParam("files[]") MultipartFile[] files, RedirectAttributes redirectAttributes) {
        List<String> imageIds = new ArrayList<>();

        for (MultipartFile file : files) {
            InputStream is = resizeImageAndGetInputStream(file);

            String fileName = String.valueOf(Calendar.getInstance().getTimeInMillis());
            DBObject metaData = new BasicDBObject();
            metaData.put("user", "TODO USER ID"); // TODO

            String imageId = fileRepository.storeFile(is, fileName, file.getContentType(), metaData);
            imageIds.add(imageId);
        }

        return imageIds;
    }

    @RequestMapping(value = "/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getImageAsResource(@PathVariable("fileId") String fileId) throws IOException {
        GridFsResource gridFsResource = fileRepository.getGridFsResource(fileId);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(gridFsResource, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{fileId}", method = RequestMethod.DELETE)
    public String deleteResource(@PathVariable("fileId") String fileId) throws IOException {
        GridFSFile gridFSFile = fileRepository.getGridFSFile(fileId);

        if (Objects.equals(gridFSFile.getMetadata().get("user"), "TODO USER ID")) { // TODO
            fileRepository.deleteFSFile(fileId);
        }

        // Returnin empty string prevents "ajax jquery delete xml parsing error no element found" in browser console
        return "";
    }


    private InputStream resizeImageAndGetInputStream(MultipartFile profilePhoto) {
        InputStream is = null;
        try {
            Image originalImage = ImageIO.read(profilePhoto.getInputStream());
            int originalWidth = originalImage.getWidth(null);
            int originalHeight = originalImage.getHeight(null);
            double aspectRatio = (double) originalWidth / (double) originalHeight;
            int imageWidth = imageMaxWidth;
            int imageHeight = (int) (imageMaxWidth / aspectRatio);
            BufferedImage resizedImage =  new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = resizedImage.createGraphics();
            graphics2D.setComposite(AlphaComposite.Src);
            //below three lines are for RenderingHints for better image quality at cost of higher processing time
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.drawImage(originalImage, 0, 0, imageMaxWidth, imageHeight, null);
            graphics2D.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpeg", baos);

            is = new ByteArrayInputStream(baos.toByteArray());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return is;
    }
}
