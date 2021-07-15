package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Employee;
import com.model.Malaika;
import com.model.Orphan;
import com.model.Orphanage;
import com.model.RequestForTransfer;
import com.model.User;
import com.service.CreationService;
import com.service.DashboardService;
import com.service.LoginService;

@Component
@ManagedBean
@SessionScoped
public class Transfering {

	@Autowired
	private CreationService create;
	@Autowired
	private LoginService logService;
	@Autowired
	private DashboardService dashboard;
	
	
	private Orphanage orphanage;
	private List<Orphan> orphanList;
	private Integer orphanNumber = 0;
	private List<Malaika> guardianList;
	private User user;
	private Malaika malaika;
	private List<Orphanage> orphanageList;
	private Orphan orphan;
	private RequestForTransfer transfer;
	
	public Transfering() {

		transfer = new RequestForTransfer();
		orphan = new Orphan();
		malaika = new Malaika();
		orphanageList = new ArrayList<Orphanage>();
		guardianList = new ArrayList<Malaika>();
		user = new User();
		orphanage = new Orphanage();
		orphanList = new ArrayList<Orphan>();
	}

	public RequestForTransfer getTransfer() {
		return transfer;
	}

	public void setTransfer(RequestForTransfer transfer) {
		this.transfer = transfer;
	}

	public Orphan getOrphan() {
		return orphan;
	}

	public void setOrphan(Orphan orphan) {
		this.orphan = orphan;
	}

	public Malaika getMalaika() {
		return malaika;
	}

	public void setMalaika(Malaika malaika) {
		this.malaika = malaika;
	}

	public List<Orphanage> getOrphanageList() {
		return orphanageList;
	}

	public void setOrphanageList(List<Orphanage> orphanageList) {
		this.orphanageList = orphanageList;
	}

	public List<Malaika> getGuardianList() {
		return guardianList;
	}

	public void setGuardianList(List<Malaika> guardianList) {
		this.guardianList = guardianList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getOrphanNumber() {
		return orphanNumber;
	}

	public void setOrphanNumber(Integer orphanNumber) {
		this.orphanNumber = orphanNumber;
	}

	public List<Orphan> getOrphanList() {
		return orphanList;
	}

	public void setOrphanList(List<Orphan> orphanList) {
		this.orphanList = orphanList;
	}

	public Orphanage getOrphanage() {
		return orphanage;
	}

	public void setOrphanage(Orphanage orphanage) {
		this.orphanage = orphanage;
	}

	public Integer orphanNum(Orphanage orphanage) {
		int i = 0;
		List<Orphan> list = new ArrayList<Orphan>();
		for (Orphan o : dashboard.orphanfROM(orphanage.getOrphanageId())) {
			if (o.getMalaika() == null) {
				if (o.getStatus().equalsIgnoreCase("Available")) {
					list.add(o);
				}
				i = list.size();
			}
		}

		return i;
	}

	public Integer orphanNum1(Malaika malaika) {
		int i = 0;
		List<Orphan> list = new ArrayList<Orphan>();
		for (Orphan o : dashboard.fromGuardian(malaika.getmID())) {
			if (o.getStatus().equalsIgnoreCase("Available")) {
				list.add(o);
			}
			i = list.size();

		}

		return i;
	}
public void transferList1() throws IOException {
	orphanList = new ArrayList<Orphan>();
	for(Orphan o: dashboard.orphanList()) {
		if(o.getMalaika() != null && o.getOrphanage() != null) {
			orphanList.add(o);
		}
	}
	FacesContext.getCurrentInstance().getExternalContext().redirect("TransferList.jsf");
}
	public void getOrphanage(Orphanage orphanage) throws IOException {
		this.orphanage = orphanage;
		orphanList = new ArrayList<Orphan>();
			
			for (Orphan o : dashboard.orphanfROM(orphanage.getOrphanageId())) {
				if (o.getMalaika() == null) {
					if (o.getStatus().equalsIgnoreCase("available")) {
						orphanList.add(o);
					}
				}
			}
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("TransferingOrphan.jsf");
	}

	public void transferPage() throws IOException {
		User us = logService.getUsername(user.getUsername());
		Employee em = logService.empID(us.getEmployee().getEmpId());
		orphanageList = new ArrayList<Orphanage>();
		for (Orphanage o : create.orphanageList()) {
			if (o.getDistrict().getDistrictID().equals(em.getDistrict().getDistrictID())) {
				orphanageList.add(o);
			}
		}
		System.out.println("output:" + user.getUsername());
		FacesContext.getCurrentInstance().getExternalContext().redirect("TransferFromOrphanage.jsf");
	}

	public void selectGuardian(Orphan orphan) throws IOException {
		this.orphan = orphan;
		User us = logService.getUsername(user.getUsername());
		Employee em = logService.empID(us.getEmployee().getEmpId());
		guardianList = new ArrayList<Malaika>();
		for (Malaika m : create.malaikaList()) {
			if (m.getDistrict().getDistrictID().equals(em.getDistrict().getDistrictID())) {
				guardianList.add(m);
			}
		}
		FacesContext.getCurrentInstance().getExternalContext().redirect("GuadianAngel.jsf");
	}

	public void transferAction() {
		Orphan orph = logService.findID(this.orphan.getOrphanId());
		// Orphanage orphanag = create.findOrphage(this.orphanage.getOrphanageId());
		Malaika guard = create.findMalaika(this.malaika.getmID());
		orph.setTransferDate(new Date());
		orph.setMalaika(guard);
		logService.update(orph);
		successMessage("success", "orphan has been transferred to" + guard.getFirstName() + " " + guard.getLastName());
		malaika = new Malaika();
		transfer = new RequestForTransfer();
		orphan = new Orphan();
	}

	public void transferAction1() {
		Orphan orph = logService.findID(this.orphan.getOrphanId());
		// Orphanage orphanag = create.findOrphage(this.orphanage.getOrphanageId());
		Malaika guard = create.findMalaika(this.transfer.getMalaika().getmID());
		RequestForTransfer r = logService.findidRequest(transfer.getTransUUID());
		if (r.getStatus().equals("Transferred")) {
			errorMessage("error", "sorry sir this request must arleady used in transfer");
		} else {
			r.setStatus("Transferred");
			logService.updateSendrequest(r);
			orph.setTransferDate(new Date());
			orph.setMalaika(guard);
			logService.update(orph);
			successMessage("success",
					"orphan has been transferred to" + guard.getFirstName() + " " + guard.getLastName());
			orphan = new Orphan();
		}
		malaika = new Malaika();
		transfer = new RequestForTransfer();
	}

	public void errorMessage(String details, String msg) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, details, msg));
	}

	public void successMessage(String details, String msg) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, details, msg));
	}

	public void sendRequest() {
		try {
			if (!this.orphanage.getOrphanageId().isEmpty()) {
				Malaika guard = create.findMalaika(this.malaika.getmID());
				Orphanage orphanag = create.findOrphage(this.orphanage.getOrphanageId());
				User us = logService.getUsername(user.getUsername());
				Employee em = logService.empID(us.getEmployee().getEmpId());
				if (em.getDistrict().getDistrictID().equals(orphanag.getDistrict().getDistrictID()) == false) {
					if (em.getDistrict().getDistrictID().equals(guard.getDistrict().getDistrictID())) {
						transfer.setTransUUID(UUID.randomUUID().toString());
						transfer.setStatus("askForOrphan");
						transfer.setMalaika(guard);
						transfer.setEmployee(em);
						transfer.setOrphanage(orphanag);
						transfer.setRequestDate(new Date());
						String msg = logService.sendRequest(transfer);
						successMessage("success", msg);
					} else {
						errorMessage("error", "Sorry sir you must choose guardian angel that is from your district");
					}
				} else {
					errorMessage("error", "sorry sir please provide orphanage from other district");

				}
			} else {
				errorMessage("error", "please provide orphanage first");
			}
			transfer = new RequestForTransfer();
			malaika = new Malaika();
			orphanage = new Orphanage();
		} catch (Exception e) {
			errorMessage("error", e.getMessage());
		}
	}

	public void guardianPage(Orphanage orphanage) throws IOException {
		this.orphanage = orphanage;
		FacesContext.getCurrentInstance().getExternalContext().redirect("MalaikaMurinzi.jsf");
	}

	public void orphanageInfo(Malaika malaika) {
		this.malaika = malaika;
	}

	public void refrest() {
		malaika = new Malaika();
	}

	public void test() {
		System.out.println("out put:" + malaika.getFirstName());
	}

	public List<RequestForTransfer> transferList() {
		User us = logService.getUsername(user.getUsername());
		Employee em = logService.empID(us.getEmployee().getEmpId());
		List<RequestForTransfer> list = new ArrayList<RequestForTransfer>();
		for (RequestForTransfer r : logService.requestforTransferList()) {
			if (r.getOrphanage().getDistrict().getDistrictID().equals(em.getDistrict().getDistrictID())) {
				list.add(r);
			}
		}
		return list;
	}

	public void showMyRequest() {

	}

	public Integer transferNumber() {
		int i = 0;
		List<RequestForTransfer> list = new ArrayList<RequestForTransfer>();
		if (user.getUsername().equalsIgnoreCase("admin")) {
			return i = 0;
		} else {
			User us = logService.getUsername(user.getUsername());
			if (us.getAccess().equalsIgnoreCase("Applicant")) {
				return i = 0;
			} else {
				Employee em = logService.empID(us.getEmployee().getEmpId());
				for (RequestForTransfer r : logService.requestforTransferList()) {
					if (r.getOrphanage().getDistrict().getDistrictID().equals(em.getDistrict().getDistrictID())
							&& r.getStatus().equals("askForOrphan")) {
						list.add(r);
					}
					i = list.size();
				}
			}
		}
		return i;
	}

}
