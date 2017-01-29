package com.anecico.fairbiomarket.web;

import com.anecico.fairbiomarket.domain.repo.FileRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import io.katharsis.resource.registry.ResourceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;
import java.util.List;

/**
 * Created by guerrpa on 23/01/2017.
 */
@RestController
public class FileController {
    @Autowired
    private FileRepository fileRepository;
    @Value("${profile.image.max.width}") private Integer imageMaxWidth;
    @Value("${profile.image.max.size}") private Double imageMaxSize;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/images", method = RequestMethod.POST)
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
