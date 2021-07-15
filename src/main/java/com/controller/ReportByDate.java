package com.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.model.Employee;
import com.model.Orphan;
import com.model.User;
import com.service.LoginService;
import com.service.ReportService;

@Component
@ManagedBean
@SessionScoped
public class ReportByDate {
	@Autowired
	private ReportService report;
	@Autowired
	private LoginService logService;
	private Orphan orphan;
	private Date startDate = null;
	private Date endDate = null;
	private User user;

	public ReportByDate() {
		orphan = new Orphan();
		user = new User();

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Orphan getOrphan() {
		return orphan;
	}

	public void setOrphan(Orphan orphan) {
		this.orphan = orphan;
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public void getuser() {
		User us = logService.getUsername(user.getUsername());
		Employee em = logService.empID(us.getEmployee().getEmpId());
		List<Orphan>list = new ArrayList<Orphan>();
		List<Orphan>list1 = new ArrayList<Orphan>();
		List<Orphan>list2 = new ArrayList<Orphan>();
		for (Orphan o : report.findBydob(startDate, endDate)) {
			if(o.getMalaika() ==null) {
				if(o.getOrphanage().getDistrict().getDistrictID().equals(em.getDistrict().getDistrictID())) {
					if(orphan.getStatus().equalsIgnoreCase("Adopted")) {
						if(o.getStatus().equals("Adopted")) {
							list.add(o);
						}
					}else if(orphan.getStatus().equalsIgnoreCase("Available")) {
						if(o.getStatus().equals("Available")) {
						list1.add(o);
						}
					}else {
						if(o.getStatus().equals("Available")) {
							list1.add(o);
							}else if(o.getStatus().equals("Adopted")) {
								list.add(o);
							}
						list2.add(o);
					}
				}
			}else {
				if(o.getMalaika().getDistrict().getDistrictID().equals(em.getDistrict().getDistrictID())) {
					if(orphan.getStatus().equalsIgnoreCase("Adopted")) {
						if(o.getStatus().equals("Adopted")) {
							list.add(o);
						}
					}else if(orphan.getStatus().equalsIgnoreCase("Available")) {
						if(o.getStatus().equals("Available")) {
						list1.add(o);
						}
					}else {
						if(o.getStatus().equals("Available")) {
							list1.add(o);
							}else if(o.getStatus().equals("Adopted")) {
								list.add(o);
							}
						list2.add(o);
					}
				}
			}
			
		}
		if(orphan.getStatus().equalsIgnoreCase("All")) {
		System.out.println("Adopted:"+list.size());
		System.out.println("Available:"+list1.size());
		System.out.println("All:"+list2.size());
		}else if(orphan.getStatus().equalsIgnoreCase("Available")) {
			System.out.println("Available:"+list1.size());
		}else if(orphan.getStatus().equalsIgnoreCase("Adopted")) {
			System.out.println("Adopted:"+list.size());
		}
		
	}

	public void report() {
		User us = logService.getUsername(user.getUsername());
		Employee em = logService.empID(us.getEmployee().getEmpId());
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			String fileName = us.getUsername()+" report.pdf";
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
			
			List<Orphan>list = new ArrayList<Orphan>();
			List<Orphan>list1 = new ArrayList<Orphan>();
			List<Orphan>list2 = new ArrayList<Orphan>();
			for (Orphan o : report.findBydob(startDate, endDate)) {
				if(o.getMalaika() ==null) {
					if(o.getOrphanage().getDistrict().getDistrictID().equals(em.getDistrict().getDistrictID())) {
						if(orphan.getStatus().equalsIgnoreCase("Adopted")) {
							if(o.getStatus().equals("Adopted")) {
								list.add(o);
							}
						}else if(orphan.getStatus().equalsIgnoreCase("Available")) {
							if(o.getStatus().equals("Available")) {
							list1.add(o);
							}
						}else {
							if(o.getStatus().equals("Available")) {
								list1.add(o);
								}else if(o.getStatus().equals("Adopted")) {
									list.add(o);
								}
							list2.add(o);
						}
					}
				}else {
					if(o.getMalaika().getDistrict().getDistrictID().equals(em.getDistrict().getDistrictID())) {
						if(orphan.getStatus().equalsIgnoreCase("Adopted")) {
							if(o.getStatus().equals("Adopted")) {
								list.add(o);
							}
						}else if(orphan.getStatus().equalsIgnoreCase("Available")) {
							if(o.getStatus().equals("Available")) {
							list1.add(o);
							}
						}else {
							if(o.getStatus().equals("Available")) {
								list1.add(o);
								}else if(o.getStatus().equals("Adopted")) {
									list.add(o);
								}
							list2.add(o);
						}
					}
				}
				
			}
			if(orphan.getStatus().equalsIgnoreCase("All")) {
				doc.add(new Paragraph("Adopted Orphan: "+list.size()));
				doc.add(new Paragraph("Available Orphan: "+list1.size()));
				doc.add(new Paragraph(orphan.getStatus() + " Orphan: "+list2.size()));
				
				}else if(orphan.getStatus().equalsIgnoreCase("Available")) {
					doc.add(new Paragraph("Available Orphan: "+list1.size()));
				}else if(orphan.getStatus().equalsIgnoreCase("Adopted")) {
					doc.add(new Paragraph("Adopted Orphan: "+list.size()));
				}
			doc.add(new Paragraph("Date:" + new SimpleDateFormat("dd/MMM/yyyy").format(new Date())));

			Paragraph p = new Paragraph(" Orphan Report in "+em.getDistrict().getName()+" District",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD, BaseColor.DARK_GRAY));
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			doc.add(new Paragraph("                                          "));
			doc.add(new Paragraph("                                          "));
			
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			doc.add(table);
			//BaseColor color = new BaseColor(10, 113, 156);

			Font font0 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.BLACK);

			PdfPCell namesCell = new PdfPCell(new Phrase("Orphan Name\n\n", font0));
			
			namesCell.setBorder(Rectangle.BOTTOM);
			table.addCell(namesCell);
			PdfPCell assignedBy = new PdfPCell(new Phrase("DateOfBirth\n\n", font0));
			
			assignedBy.setBorder(Rectangle.BOTTOM);
			table.addCell(assignedBy);
			PdfPCell givenDate = new PdfPCell(new Phrase("Status\n\n", font0));
			
			givenDate.setBorder(Rectangle.BOTTOM);
			table.addCell(givenDate);
			PdfPCell totalCost = new PdfPCell(new Phrase("Gender\n\n", font0));
			
			totalCost.setBorder(Rectangle.BOTTOM);
			table.addCell(totalCost);
			
			for (Orphan o : report.findBydob(startDate, endDate)) {
				if(o.getMalaika() == null) {
					if(o.getOrphanage().getDistrict().getDistrictID().equalsIgnoreCase(em.getDistrict().getDistrictID())) {
						if(orphan.getStatus().equalsIgnoreCase("All")) {
							PdfPCell names = new PdfPCell(new Phrase(o.getFirstName()+" "+o.getLastName()));
							names.setBorder(Rectangle.NO_BORDER);
							table.addCell(names);
							PdfPCell date = new PdfPCell(new Phrase(new SimpleDateFormat("yyyy-MM-dd").format(o.getDob())));
							date.setBorder(Rectangle.NO_BORDER);
							table.addCell(date);
							PdfPCell status = new PdfPCell(new Phrase(o.getStatus()));
							status.setBorder(Rectangle.NO_BORDER);
							table.addCell(status);
							PdfPCell gender = new PdfPCell(new Phrase(o.getGender()));
							gender.setBorder(Rectangle.NO_BORDER);
							table.addCell(gender);
						}else if(orphan.getStatus().equalsIgnoreCase(o.getStatus())) {
							PdfPCell names = new PdfPCell(new Phrase(o.getFirstName()+" "+o.getLastName()));
							names.setBorder(Rectangle.NO_BORDER);
							table.addCell(names);
							PdfPCell date = new PdfPCell(new Phrase(new SimpleDateFormat("yyyy-MM-dd").format(o.getDob())));
							date.setBorder(Rectangle.NO_BORDER);
							table.addCell(date);
							PdfPCell status = new PdfPCell(new Phrase(o.getStatus()));
							status.setBorder(Rectangle.NO_BORDER);
							table.addCell(status);
							PdfPCell gender = new PdfPCell(new Phrase(o.getGender()));
							gender.setBorder(Rectangle.NO_BORDER);
							table.addCell(gender);
						}
					}
				}else {
					if(o.getMalaika().getDistrict().getDistrictID().equalsIgnoreCase(em.getDistrict().getDistrictID())) {
						if(orphan.getStatus().equalsIgnoreCase("All")) {
							PdfPCell names = new PdfPCell(new Phrase(o.getFirstName()+" "+o.getLastName()));
							names.setBorder(Rectangle.NO_BORDER);
							table.addCell(names);
							PdfPCell date = new PdfPCell(new Phrase(new SimpleDateFormat("yyyy-MM-dd").format(o.getDob())));
							date.setBorder(Rectangle.NO_BORDER);
							table.addCell(date);
							PdfPCell status = new PdfPCell(new Phrase(o.getStatus()));
							status.setBorder(Rectangle.NO_BORDER);
							table.addCell(status);
							PdfPCell gender = new PdfPCell(new Phrase(o.getGender()));
							gender.setBorder(Rectangle.NO_BORDER);
							table.addCell(gender);
						}else if(orphan.getStatus().equalsIgnoreCase(o.getStatus())) {
							PdfPCell names = new PdfPCell(new Phrase(o.getFirstName()+" "+o.getLastName()));
							names.setBorder(Rectangle.NO_BORDER);
							table.addCell(names);
							PdfPCell date = new PdfPCell(new Phrase(new SimpleDateFormat("yyyy-MM-dd").format(o.getDob())));
							date.setBorder(Rectangle.NO_BORDER);
							table.addCell(date);
							PdfPCell status = new PdfPCell(new Phrase(o.getStatus()));
							status.setBorder(Rectangle.NO_BORDER);
							table.addCell(status);
							PdfPCell gender = new PdfPCell(new Phrase(o.getGender()));
							gender.setBorder(Rectangle.NO_BORDER);
							table.addCell(gender);
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
	

}
