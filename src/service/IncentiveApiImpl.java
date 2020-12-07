package service;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.Special;
import dao.Vehicle;
import dao.VehicleModel;
import dto.AbstractPersistent;
import dto.DataPersistence;

public final class IncentiveApiImpl implements IncentiveApi {

	private static AbstractPersistent dao;

	public IncentiveApiImpl() {
		dao = new DataPersistence();
	}

	public static Date getCurrentTime() {
		Date currentTime = new Date();
		return currentTime;
	}

	public static boolean timeCheck(Date startDate, Date endDate, Date currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String sd = formatter.format(startDate);
		String ed = formatter.format(endDate);
		String ct = formatter.format(currentTime);
		if (sd.equals(ed) || ed.equals(ct)) {
			return true;
		}
		return startDate.before(currentTime) && endDate.after(currentTime);
	}


	// read price after discount here
	// Can an incentive be both percent and value discount?
	// Vehicle: price 的 string 是只是有数字还是有别的? integer or float?

	/*
	 * Assumption: 1. An incentive can not be percentage discount and direct
	 * discount at the same time 2. Vehicle: Price string only contains float
	 * numbers
	 */
	@Override
	public VehicleModel updateSpecialPrice(Vehicle vehicle) {
		// get vehicle id from

		// read all incentive from dao

		// find a certain incentive rule

		// calc special price

		// save all UI necessary value to VehicleModel

		return new VehicleModel(vehicle, getSpecialTest(""));


		/*
		List<String> idList = s.getScope();
		List<Vehicle> vList = dao.getAllVehicles();

		if (timeCheck(s.getStartDate(), s.getEndDate(), getCurrentTime())) {

			for (int i = 0; i < idList.size(); i++) {
				for (int j = 0; j < vList.size(); j++) {
					if (idList.get(i).equals(vList.get(j).getVehicleId())) {
						if (s.getDiscountValue() != 0) {
							try {
								float price = Float.parseFloat(vList.get(j).getPrice());
								vList.get(j).setPrice(price - s.getDiscountValue() + "");
							} catch (NumberFormatException e) {
								e.printStackTrace();
							}
						} else if (s.getDiscountPercent() != 0) {
							try {
								float price = Float.parseFloat(vList.get(j).getPrice());
								vList.get(j).setPrice(price * ((100 - s.getDiscountPercent()) / 100) + "");
							} catch (NumberFormatException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		*/
	}

	// read title, description, discount value(percentage) and dates here
	@Override
	public Special getSpecial(String specialId) {

		List<Special> list = dao.getAllSpecials();

		for (int i = 0; i < list.size(); i++) {
			if (specialId.equals(list.get(i).getSpecialId())) {
				return list.get(i);
			}
		}
		return null;
	}

	// read incentive types here
	@Override
	public String incentiveType(String specialId) {
		Special s = getSpecial(specialId);

		StringBuilder sb = new StringBuilder();
		if (s.getIsValidOnCashPayment() == true) {
			sb.append(" Cash payment discount ");
		}
		if (s.getIsValidOnCheckPayment() == true) {
			sb.append(" Check payment discount ");
		}
		if (s.getIsValidOnLease() == true) {
			sb.append(" Lease discount ");
		}
		if (s.getIsValidOnLoan() == true) {
			sb.append(" Loan discount ");
		}
		if (sb.length() == 0) {
			return " No special price for this vehicle at this moment ";

		}
		return sb.toString().replace("  ", " & ");
	}


	/**
	 * // TODO: remove after finished
	 * only for test
	 * @param specialid
	 * @return
	 */
    public Special getSpecialTest(String specialid) {

        Calendar calStart = Calendar.getInstance();
        calStart.add(Calendar.DAY_OF_MONTH, -5);
        Calendar calEnd = Calendar.getInstance();
        calEnd.add(Calendar.SECOND, 15);

		Special obj = new Special();
        obj.setStartDate(calStart.getTime());
        obj.setEndDate(calEnd.getTime());
        obj.setTitle("Incentive demo");
        obj.setDescription("Demo description  XXXXXX");
        obj.setDisclaimer("Demo disclaimer XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        obj.setValue("500");
        obj.setBrand("Honda");
        return obj;
    }

}
