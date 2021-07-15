package com.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.model.Malaika;
import com.model.Orphan;
import com.model.Orphanage;
import com.model.Province;
import com.model.Request;
import com.service.CreationService;
import com.service.DashboardService;
import com.service.ReportService;

@Component
@ManagedBean
@SessionScoped
public class Report {

	@Autowired
	private ReportService report;
	@Autowired
	private CreationService create;
	@Autowired
	private DashboardService dashboard;
	
	private Request request;
	private Orphan orphan;
	private Province province;
	private List<Province> provinceList;
	private List<Orphan> orphanList;
	private String d = null;
	private String orphanData = new String();
	private List<Orphan> orphanList1;
	private Orphanage orphanage;
	private Malaika malaika;

	public Report() {
		malaika = new Malaika();
		orphanage = new Orphanage();
		orphanList = new ArrayList<Orphan>();
		request = new Request();
		orphan = new Orphan();
		province = new Province();
		provinceList = new ArrayList<Province>();
		orphanList = new ArrayList<Orphan>();
	}

	public Malaika getMalaika() {
		return malaika;
	}

	public void setMalaika(Malaika malaika) {
		this.malaika = malaika;
	}

	public Orphanage getOrphanage() {
		return orphanage;
	}

	public void setOrphanage(Orphanage orphanage) {
		this.orphanage = orphanage;
	}

	public List<Orphan> getOrphanList1() {
		return orphanList1;
	}

	public void setOrphanList1(List<Orphan> orphanList1) {
		this.orphanList1 = orphanList1;
	}

	public String getOrphanData() {
		return orphanData;
	}

	public void setOrphanData(String orphanData) {
		this.orphanData = orphanData;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public List<Orphan> getOrphanList() {
		return orphanList;
	}

	public void setOrphanList(List<Orphan> orphanList) {
		this.orphanList = orphanList;
	}

	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Orphan getOrphan() {
		return orphan;
	}

	public void setOrphan(Orphan orphan) {
		this.orphan = orphan;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}


	@PostConstruct
	public void init() {
		for (Province p : create.provinceList()) {
			provinceList.add(p);
		}
	}

	public void edit(Orphan orphan) {
		this.orphan = orphan;
	}

	public void updateOrphan() {
		try {
			String msg = report.updateOrphan(orphan);
			successMessage("success", msg);
			orphanList = report.allOrphan();
		} catch (Exception e) {
			// String msg = report.updateOrphan(o);
			errorMessage("error", e.getMessage());
		}
	}

	public void document(Orphan orphan) {
		this.orphan = orphan;

		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File("E:\\alfred\\eclipse\\work\\OCAMIS\\src\\main\\webapp\\resources\\File\\"
						+ this.orphan.getFile());
				Desktop.getDesktop().open(myFile);
			} catch (IOException ex) {
				errorMessage(ex.getMessage(), "");
				ex.getMessage();
			}
		}
	}

	public Date retransform(LocalDateTime ldt) {
		Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		return out;
	}

	public void listOfOrphan() throws IOException {
		orphanList = new ArrayList<Orphan>();
		orphanList = report.allOrphan();
		FacesContext.getCurrentInstance().getExternalContext().redirect("OrphanAdmin.jsf");
	}

	public void orphanageListAdmin() throws IOException {
		orphanList = report.allOrphan();
		FacesContext.getCurrentInstance().getExternalContext().redirect("Orphanageadmin.jsf");
	}
	public void orphanByOrphanage(Orphanage orphanage) throws IOException {
		this.orphanage = orphanage;
		orphanList = new ArrayList<Orphan>();
		for (Orphan orphan1 : dashboard.orphanfROM(orphanage.getOrphanageId())) {
			if (orphan1.getMalaika() == null) {
				if (orphan1.getStatus().equalsIgnoreCase("Available")) {
					orphanList.add(orphan1);
				}
			}
		}
		FacesContext.getCurrentInstance().getExternalContext().redirect("OrphanAdmin.jsf");
	}
	public void orphanByGuadian(Malaika malaika) throws IOException {
		this.malaika = malaika;
		orphanList = new ArrayList<Orphan>();
		for (Orphan orphan1 : dashboard.fromGuardian(malaika.getmID())) {
			if (orphan1.getStatus().equalsIgnoreCase("Available")) {
			orphanList.add(orphan1);
			}
		}
		FacesContext.getCurrentInstance().getExternalContext().redirect("OrphanAdmin.jsf");
	}

	public void requestReport() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			String fileName = "request.pdf";
			String contentType = "application/pdf";
			ec.responseReset();
			ec.setResponseContentType(contentType);
			ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			OutputStream out = ec.getResponseOutputStream();
			Document doc = new Document();
			PdfWriter.getInstance(doc, out);
			LineSeparator ls = new LineSeparator();
			doc.open();
			Image img = Image.getInstance("E:\\ncc1.PNG");
			img.scaleAbsolute(150f, 100f);
			Paragraph header = new Paragraph();
			header.add(img);
			header.setAlignment(Image.ALIGN_LEFT);

			doc.add(header);
			doc.add(new Chunk(ls));

			doc.add(new Paragraph(
					"National Commission For Children,\nP.O.Box 6129 - Kigali,Rwanda\nPhone : (+250) 252 581 221"
							+ "\nEmail : NCC@gmail.com"));
			doc.add(new Paragraph("                                          "));
			doc.add(new Paragraph("                                          "));
			List<Request> list = new ArrayList<Request>();
			if (request.getStatus().equalsIgnoreCase("All")) {
				list = report.allRequest();
			} else {
				list = report.selectByStatus(request.getStatus());
			}
			int y = list.size();
			doc.add(new Paragraph("Request Size:" + y));
			doc.add(new Paragraph("Date:" + new SimpleDateFormat("dd/MMM/yyyy").format(new Date())));

			Paragraph p = new Paragraph(request.getStatus() + " Request Report",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.DARK_GRAY));
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			doc.add(new Paragraph("                                          "));
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			doc.add(table);
			BaseColor color = new BaseColor(10, 113, 156);

			Font font0 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.WHITE);

			PdfPCell namesCell = new PdfPCell(new Phrase("ApplicantName\n\n", font0));
			namesCell.setBackgroundColor(color);
			table.addCell(namesCell);
			PdfPCell assignedBy = new PdfPCell(new Phrase("Applied Date\n\n", font0));
			assignedBy.setBackgroundColor(color);
			table.addCell(assignedBy);
			PdfPCell givenDate = new PdfPCell(new Phrase("Assigned to\n\n", font0));
			givenDate.setBackgroundColor(color);
			table.addCell(givenDate);
			PdfPCell totalCost = new PdfPCell(new Phrase("Status\n\n", font0));
			totalCost.setBackgroundColor(color);
			table.addCell(totalCost);

			if (request.getStatus().equalsIgnoreCase("All")) {
				for (Request rr : report.allRequest()) {
					table.addCell(rr.getApplicant().getFirstName() + " " + rr.getApplicant().getLastName());

					table.addCell(new SimpleDateFormat("dd/MM/yyyy").format(rr.getStartDate()) + "");
					if (rr.getEmployee() != null) {
						table.addCell(rr.getEmployee().getFirstName() + " " + rr.getEmployee().getLastName());
					} else {
						table.addCell("Not Assigned");
					}
					table.addCell(rr.getStatus() + "");
				}
			} else {
				for (Request r : report.selectByStatus(request.getStatus())) {
					table.addCell(r.getApplicant().getFirstName() + " " + r.getApplicant().getLastName());

					table.addCell(new SimpleDateFormat("dd/MM/yyyy").format(r.getStartDate()) + "");
					if (r.getEmployee() != null) {
						table.addCell(r.getEmployee().getFirstName() + " " + r.getEmployee().getLastName());
					} else {
						table.addCell("Not Assigned");
					}
					table.addCell(r.getStatus() + "");
				}
			}

			doc.add(table);
			doc.add(new Paragraph("                                          "));
			doc.add(new Paragraph("                                          "));
			String d = new SimpleDateFormat("dd/MMM/yyyy HH:mm").format(new Date());
			Paragraph printedOn = new Paragraph("Printed On:" + d);
			printedOn.setAlignment(Element.ALIGN_RIGHT);
			doc.add(printedOn);
			doc.close();

			fc.responseComplete();

		} catch (Exception ex) {
			System.err.println("Error:" + ex.getMessage());
			errorMessage("Error:", ex.getMessage());
		}
	}

	public void OrphanReport() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			String fileName = "Orphan.pdf";
			String contentType = "application/pdf";
			ec.responseReset();
			ec.setResponseContentType(contentType);
			ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			OutputStream out = ec.getResponseOutputStream();
			Document doc = new Document();
			PdfWriter.getInstance(doc, out);
			LineSeparator ls = new LineSeparator();
			doc.open();
			Image img = Image.getInstance("E:\\ncc.JPG");
			img.scaleAbsolute(150f, 100f);
			Paragraph header = new Paragraph();
			header.add(img);
			Paragraph header1 = new Paragraph("National Commission For Children,\nP.O.Box 6129 - Kigali,Rwanda\nPhone : (+250) 788 432945"
					+ "\nEmail : NccRwanda@gmail.com");
			//header.setAlignment(Element.ALIGN_RIGHT);
			header.setAlignment(Image.ALIGN_LEFT+Element.ALIGN_RIGHT);
			header.add(header1);
			doc.add(header);
			doc.add(new Chunk(ls));
			doc.add(new Paragraph("                                          "));
			List<Orphan> list1 = new ArrayList<Orphan>();
			List<Orphan> list2 = new ArrayList<Orphan>();
			List<Orphan> list3 = new ArrayList<Orphan>();
			if (orphan.getStatus().equalsIgnoreCase("All")) {
				for (Orphan o : report.SelectByStatus("Available")) {
					if (o.getMalaika() == null) {
						if (o.getOrphanage().getDistrict().getProvince().getProvinceId()
								.equalsIgnoreCase(province.getProvinceId())) {
							list2.add(o);
						}
					} else if (o.getMalaika() != null) {
						if (o.getMalaika().getDistrict().getProvince().getProvinceId()
								.equals(province.getProvinceId())) {
							list2.add(o);
						}
					}
				}
				for (Orphan o : report.SelectByStatus("Adopted")) {
					if (o.getMalaika() == null) {
						if (o.getOrphanage().getDistrict().getProvince().getProvinceId()
								.equalsIgnoreCase(province.getProvinceId())) {
							list3.add(o);
						}
					} else if (o.getMalaika() != null) {
						if (o.getMalaika().getDistrict().getProvince().getProvinceId()
								.equals(province.getProvinceId())) {
							list3.add(o);
						}
					}
				}
				for (Orphan o : report.allOrphan()) {
					if (o.getMalaika() == null) {
						if (o.getOrphanage().getDistrict().getProvince().getProvinceId()
								.equalsIgnoreCase(province.getProvinceId())) {
							list1.add(o);
						}
					} else if (o.getMalaika() != null) {
						if (o.getMalaika().getDistrict().getProvince().getProvinceId()
								.equals(province.getProvinceId())) {
							list1.add(o);
						}
					}
				}
				int y = list2.size();
				Paragraph doc1 = new Paragraph("Availble Orphan: " + y);

				doc.add(new Paragraph(doc1));
				int yy = list3.size();
				Paragraph doc2 = new Paragraph("Adopted Orphan: " + yy);

				doc.add(new Paragraph(doc2));

			} else {
				for (Orphan o : report.SelectByStatus(orphan.getStatus())) {
					if (o.getMalaika() == null) {
						if (o.getOrphanage().getDistrict().getProvince().getProvinceId()
								.equalsIgnoreCase(province.getProvinceId())) {
							list1.add(o);
						}
					} else if (o.getMalaika() != null) {
						if (o.getMalaika().getDistrict().getProvince().getProvinceId()
								.equals(province.getProvinceId())) {
							list1.add(o);
						}
					}
				}
			}
			int y = list1.size();
			Paragraph pa = new Paragraph(orphan.getStatus() + " Orphan: " + y);

			doc.add(pa);
			Paragraph da = new Paragraph("Date:" + new SimpleDateFormat("dd/MMM/yyyy").format(new Date()));

			doc.add(da);
			Province pro = report.getProvince(province.getProvinceId());
			Paragraph p = new Paragraph(orphan.getStatus() + " Orphan Report from " + pro.getName() + " Province",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.DARK_GRAY));
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			doc.add(new Paragraph("                                          "));
			PdfPTable table = new PdfPTable(7);
			table.setWidthPercentage(100);
			doc.add(table);
			BaseColor color = new BaseColor(10, 113, 156);

			Font font0 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.WHITE);

			PdfPCell namesCell = new PdfPCell(new Phrase("Orphan Name", font0));
			namesCell.setBackgroundColor(color);
			namesCell.setBorder(Rectangle.NO_BORDER);
			
			table.addCell(namesCell);
			PdfPCell assignedBy = new PdfPCell(new Phrase("Date Of Birth", font0));
			assignedBy.setBackgroundColor(color);
			assignedBy.setBorder(Rectangle.NO_BORDER);
			table.addCell(assignedBy);
			PdfPCell province1 = new PdfPCell(new Phrase("Province", font0));
			province1.setBackgroundColor(color);
			province1.setBorder(Rectangle.NO_BORDER);
			table.addCell(province1);
			PdfPCell district1 = new PdfPCell(new Phrase("District", font0));
			district1.setBackgroundColor(color);
			district1.setBorder(Rectangle.NO_BORDER);
			table.addCell(district1);
			PdfPCell home = new PdfPCell(new Phrase("Home", font0));
			home.setBackgroundColor(color);
			home.setBorder(Rectangle.NO_BORDER);
			table.addCell(home);
			PdfPCell name = new PdfPCell(new Phrase("Name", font0));
			name.setBackgroundColor(color);
			name.setBorder(Rectangle.NO_BORDER);
			table.addCell(name);
			PdfPCell status = new PdfPCell(new Phrase("Status", font0));
			status.setBackgroundColor(color);
			status.setBorder(Rectangle.NO_BORDER);
			table.addCell(status);

			if (orphan.getStatus().equalsIgnoreCase("All")) {
				for (Orphan r : report.allOrphan()) {
					if (r.getMalaika() == null) {
						if (r.getOrphanage().getDistrict().getProvince().getProvinceId()
								.equalsIgnoreCase(province.getProvinceId())) {
							PdfPCell firstName = new PdfPCell(new Phrase(r.getFirstName() + " " + r.getLastName()));
							firstName.setBorder(Rectangle.NO_BORDER);
							table.addCell(firstName);
							PdfPCell dob = new PdfPCell(
									new Phrase(new SimpleDateFormat("yyyy/MM/dd").format(r.getDob())));
							dob.setBorder(Rectangle.NO_BORDER);
							table.addCell(dob);
							PdfPCell provi = new PdfPCell(
									new Phrase(r.getOrphanage().getDistrict().getProvince().getName()));
							provi.setBorder(Rectangle.NO_BORDER);
							table.addCell(provi);
							PdfPCell dist = new PdfPCell(new Phrase(r.getOrphanage().getDistrict().getName()));
							dist.setBorder(Rectangle.NO_BORDER);
							table.addCell(dist);
							PdfPCell hom = new PdfPCell(new Phrase("Orphanage"));
							hom.setBorder(Rectangle.NO_BORDER);
							table.addCell(hom);
							PdfPCell nam = new PdfPCell(new Phrase(r.getOrphanage().getName()));
							nam.setBorder(Rectangle.NO_BORDER);
							table.addCell(nam);
							PdfPCell stat = new PdfPCell(new Phrase(r.getStatus()));
							stat.setBorder(Rectangle.NO_BORDER);
							table.addCell(stat);
						}
					} else if (r.getMalaika() != null) {
						if (r.getMalaika().getDistrict().getProvince().getProvinceId()
								.equalsIgnoreCase(province.getProvinceId())) {
							PdfPCell firstName = new PdfPCell(new Phrase(r.getFirstName() + " " + r.getLastName()));
							firstName.setBorder(Rectangle.NO_BORDER);
							table.addCell(firstName);
							PdfPCell dob = new PdfPCell(
									new Phrase(new SimpleDateFormat("yyyy/MM/dd").format(r.getDob())));
							dob.setBorder(Rectangle.NO_BORDER);
							table.addCell(dob);
							PdfPCell provi = new PdfPCell(
									new Phrase(r.getMalaika().getDistrict().getProvince().getName()));
							provi.setBorder(Rectangle.NO_BORDER);
							table.addCell(provi);
							PdfPCell dist = new PdfPCell(new Phrase(r.getMalaika().getDistrict().getName()));
							dist.setBorder(Rectangle.NO_BORDER);
							table.addCell(dist);
							PdfPCell hom = new PdfPCell(new Phrase("Guardian angel"));
							hom.setBorder(Rectangle.NO_BORDER);
							table.addCell(hom);
							PdfPCell nam = new PdfPCell(
									new Phrase(r.getMalaika().getFirstName() + " " + r.getMalaika().getLastName()));
							nam.setBorder(Rectangle.NO_BORDER);
							table.addCell(nam);
							PdfPCell stat = new PdfPCell(new Phrase(r.getStatus()));
							stat.setBorder(Rectangle.NO_BORDER);
							table.addCell(stat);
						}
					}

				}
			} else {
				for (Orphan r : report.SelectByStatus(orphan.getStatus())) {

					if (r.getMalaika() == null) {
						if (r.getOrphanage().getDistrict().getProvince().getProvinceId()
								.equalsIgnoreCase(province.getProvinceId())) {
							PdfPCell firstName = new PdfPCell(new Phrase(r.getFirstName() + " " + r.getLastName()));
							firstName.setBorder(Rectangle.NO_BORDER);
							table.addCell(firstName);
							PdfPCell dob = new PdfPCell(
									new Phrase(new SimpleDateFormat("yyyy/MM/dd").format(r.getDob())));
							dob.setBorder(Rectangle.NO_BORDER);
							table.addCell(dob);
							PdfPCell provi = new PdfPCell(
									new Phrase(r.getOrphanage().getDistrict().getProvince().getName()));
							provi.setBorder(Rectangle.NO_BORDER);
							table.addCell(provi);
							PdfPCell dist = new PdfPCell(new Phrase(r.getOrphanage().getDistrict().getName()));
							dist.setBorder(Rectangle.NO_BORDER);
							table.addCell(dist);
							PdfPCell hom = new PdfPCell(new Phrase("Orphanage"));
							hom.setBorder(Rectangle.NO_BORDER);
							table.addCell(hom);
							PdfPCell nam = new PdfPCell(new Phrase(r.getOrphanage().getName()));
							nam.setBorder(Rectangle.NO_BORDER);
							table.addCell(nam);
							PdfPCell stat = new PdfPCell(new Phrase(r.getStatus()));
							stat.setBorder(Rectangle.NO_BORDER);
							table.addCell(stat);
						}
					} else if (r.getMalaika() != null) {
						if (r.getMalaika().getDistrict().getProvince().getProvinceId()
								.equalsIgnoreCase(province.getProvinceId())) {
							PdfPCell firstName = new PdfPCell(new Phrase(r.getFirstName() + " " + r.getLastName()));
							firstName.setBorder(Rectangle.NO_BORDER);
							table.addCell(firstName);
							PdfPCell dob = new PdfPCell(
									new Phrase(new SimpleDateFormat("yyyy/MM/dd").format(r.getDob())));
							dob.setBorder(Rectangle.NO_BORDER);
							table.addCell(dob);
							PdfPCell provi = new PdfPCell(
									new Phrase(r.getMalaika().getDistrict().getProvince().getName()));
							provi.setBorder(Rectangle.NO_BORDER);
							table.addCell(provi);
							PdfPCell dist = new PdfPCell(new Phrase(r.getMalaika().getDistrict().getName()));
							dist.setBorder(Rectangle.NO_BORDER);
							table.addCell(dist);
							PdfPCell hom = new PdfPCell(new Phrase("Guardian angel"));
							hom.setBorder(Rectangle.NO_BORDER);
							table.addCell(hom);
							PdfPCell nam = new PdfPCell(
									new Phrase(r.getMalaika().getFirstName() + " " + r.getMalaika().getLastName()));
							nam.setBorder(Rectangle.NO_BORDER);
							table.addCell(nam);
							PdfPCell stat = new PdfPCell(new Phrase(r.getStatus()));
							stat.setBorder(Rectangle.NO_BORDER);
							table.addCell(stat);
						}
					}

				}
			}

			doc.add(table);
			doc.add(new Paragraph("                                          "));
			doc.add(new Paragraph("                                          "));
			String d = new SimpleDateFormat("dd/MMM/yyyy HH:mm").format(new Date());
			Paragraph printedOn = new Paragraph("Printed On:" + d);
			printedOn.setAlignment(Element.ALIGN_RIGHT);
			doc.add(printedOn);
			doc.close();

			fc.responseComplete();

		} catch (Exception ex) {
			System.err.println("Error:" + ex.getMessage());
			errorMessage("Error:", ex.getMessage());
		}
	}

	public void errorMessage(String details, String msg) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, details, msg));
	}

	public void successMessage(String details, String msg) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, details, msg));
	}

	public void display() {
		System.out.println(request.getStatus());
	}
}
