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
import com.googlecode.objectify.ObjectifyService;

import fr.devoxx.lepresidentest.entity.President;

public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4119453875625910502L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		//On récupère les clés du blobstore
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		President president = new President();
		president.images = blobs.get("image").get(0);
		president.name = req.getParameter("name");
		ObjectifyService.begin().put(president);
		resp.sendRedirect("/");
	}
}
