package fr.devoxx.lepresidentest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import fr.devoxx.lepresidentest.entity.President;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
                ImagesService imagesService = ImagesServiceFactory.getImagesService();
                //On récupère les clés du blobstore
                Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
                President president = new President();
                president.image = imagesService.getServingUrl(blobs.get("image").get(0));
                president.name = req.getParameter("name");
                ObjectifyService.begin().put(president);
                resp.sendRedirect("/");
        }
}