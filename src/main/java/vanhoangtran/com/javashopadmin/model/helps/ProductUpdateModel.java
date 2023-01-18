package vanhoangtran.com.javashopadmin.model.helps;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.ProductAttributeBean;
import vanhoangtran.com.javashopadmin.model.bean.ProductBean;
import vanhoangtran.com.javashopadmin.model.bean.ProductPhotoBean;

public class ProductUpdateModel {
	private ProductBean data;
	private ArrayList<ProductAttributeBean> dataOfAttributes;
	private ArrayList<ProductPhotoBean> dataOfPhotos;

	public ProductUpdateModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductUpdateModel(ProductBean data, ArrayList<ProductAttributeBean> dataOfAttributes,
			ArrayList<ProductPhotoBean> dataOfPhotos) {
		super();
		this.data = data;
		this.dataOfAttributes = dataOfAttributes;
		this.dataOfPhotos = dataOfPhotos;
	}

	public ProductBean getData() {
		return data;
	}

	public void setData(ProductBean data) {
		this.data = data;
	}

	public ArrayList<ProductAttributeBean> getDataOfAttributes() {
		return dataOfAttributes;
	}

	public void setDataOfAttributes(ArrayList<ProductAttributeBean> dataOfAttributes) {
		this.dataOfAttributes = dataOfAttributes;
	}

	public ArrayList<ProductPhotoBean> getDataOfPhotos() {
		return dataOfPhotos;
	}

	public void setDataOfPhotos(ArrayList<ProductPhotoBean> dataOfPhotos) {
		this.dataOfPhotos = dataOfPhotos;
	}

}
