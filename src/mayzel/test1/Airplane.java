package mayzel.test1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mayzel.vendingmachine.Item;
import mayzel.test1.Seat;
import mayzel.test1.UnknownSeatException;

public class Airplane {
	private Map<String,Seat> map;
	private Seat array[][];
	private String [] conf;
	private String configuration;
	private ArrayList<Seat> list;

	/**
	 * Construct a new Airplane with the specified configuration and number of
	 * rows. The configuration is a String with letters specifying a seat's
	 * position in the row and a "_" for the aisle.
	 * 
	 * For instance, an Airplane with configuration, ABC_DEFGH_JKL would be
	 * three seats, then an aisle, then 5 seats, then an aisle, then 3 seats.
	 * 
	 * @param configuration
	 * @param numRows
	 */
	public Airplane(String configuration, int numRows) {
		this.configuration = configuration;
		map = new HashMap<String,Seat>();
		list = new ArrayList<Seat>();
		array = new Seat[numRows][];
		conf = configuration.split("_");
		array = new Seat[numRows][configuration.length()];
		for(int i=0;i<numRows;i++){
			for(int j=0;j<configuration.length();j++){
				if(configuration.charAt(j)!=('_')){
					array[i][j] = new Seat(i+1, configuration.charAt(j));
					list.add(array[i][j]);
					String code = array[i][j].getCode();
					map.put(code, array[i][j]);	
				}
			}
		}
	}

	/**
	 * @return the total number of EMPTy seats on the plane.
	 */
	public int getNumEmptySeats() {
		int totalEmp =0;
		for (int i = 0; i < list.size(); i++) {
				if (list.get(i).isOccupied()==false) {
					totalEmp++;
				}
		}
		return totalEmp;
	}

	/**
	 * @return true if the plane is full, otherwise false.
	 */
	public boolean isFull() {
		if (this.getNumEmptySeats() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param code
	 * @return true if the seat is occupied, otherwise false.
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public boolean isOccupied(String code) throws UnknownSeatException {
		if(map.get(code)==null){
			throw new UnknownSeatException();
		}
		if (map.get(code).isOccupied()) {
			return true;
		}
		return false;
	}

	/**
	 * Sets the seat as occupied/unoccupied
	 * 
	 * @param code
	 * @param occupied
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void setOccupied(String code, boolean occupied) throws UnknownSeatException {
		map.get(code).setOccupied(occupied);
		if(map.get(code)==null){
			throw new UnknownSeatException();
		}
	}

	/**
	 * Set all seats by their codes as occupied
	 * 
	 * @param codes
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void occupy(String... codes) throws UnknownSeatException {
		for(String code: codes){
			if(map.containsKey(code)){
			map.get(code).setOccupied(true);
		}
			else{
				throw new UnknownSeatException();
			}
		}
		
	}

	/**
	 * Sets all seats as occupied
	 * 
	 * @param seats
	 */
	public void occupy(List<Seat> seats) {
		for (int i = 0; i < seats.size(); i++) {
			seats.get(i).setOccupied(true);
		}
		
	}

	/**
	 * Returns the seat specified by it's code
	 * 
	 * @param code
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public Seat getSeat(String code) throws UnknownSeatException {
	    for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCode().equals(code)) {
					return map.get(code);
				}
		}
		throw new UnknownSeatException();
	}

	/**
	 * @return total number of seats on the plane
	 */
	public int getNumSeats() {
		int total = 0;
		for(int i=0;i<array.length;i++){
			for(int j=0; j<array[i].length-(conf.length-1);j++){
				total++;
			}
		}
		return total;
	}

	/**
	 * Returns the Airplane specified in text format.
	 * 
	 * The first line should be the configuration, prepended by 4 spaces Each
	 * row in the plane gets a line which starts with The row number, padded
	 * with leading zeros so that is is always 3 digits. A space Then for each
	 * seat, either a "." for an empty seat, "O" for an occupied seat and "_"
	 * for an aisle.
	 * 
	 * Example. AB_CD_EF\n 001 .._.._..\n 002 .._.._..\n 003 .._.._..\n
	 * 
	 * 
	 */
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("000");
		int num =1;
		StringBuilder info = new StringBuilder();
		info.append("    "+configuration+"\n");
		for(int i=0;i<array.length;i++){
			info.append(formatter.format(num++));
			info.append(" ");
			for(int j=0; j<configuration.length();j++){
				if(configuration.charAt(j)!=('_')){
					if(this.array[i][j].isOccupied()==true){
						info.append("O");
					}
					else if(this.array[i][j].isOccupied()==false){
						info.append(".");
					}
				}
				else{
					info.append("_");
				}
			}
			info.append("\n");
		}	
		return info.toString();
	}

	/**
	 * 
	 * @param numSeatsTogeather
	 *            the number of seats to occupy.
	 * @return A list of occupied seats.
	 * @throws FullPlaneException
	 *             if the plane is full
	 * @throws NotEnoughSeatsTogeatherException
	 *             if there are not enough seats next to each other.
	 */
	public List<Seat> occupySeats(int numSeatsTogether) throws FullPlaneException, NotEnoughSeatsTogeatherException {
		Seat s;
		if(isFull()){
			throw new FullPlaneException();
		}
		List<Seat> print = new ArrayList<Seat>();
		for(int i=0;i<array.length;i++){
			for(int j=0; j<configuration.length();j++){
				s = new Seat(i+1, configuration.charAt(j));
				print.add(s);
				if(configuration.charAt(j)=='_' || s.isOccupied()){
					print.clear();
					i++;
				}
				if(print.size()== numSeatsTogether){
					return print;
				}
			}
		}
		throw new NotEnoughSeatsTogeatherException(); 
	}

}
