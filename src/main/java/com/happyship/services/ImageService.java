package com.happyship.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyship.dao.ImageDAO;
import com.happyship.entities.Image;

@Service
public class ImageService implements IImageService {
	@Autowired
	private ImageDAO imageDAO;

	public Integer saveImage(Image image) {
		imageDAO.save(image);
		return image.getId();
	}
}
