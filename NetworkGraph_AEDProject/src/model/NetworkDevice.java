package model;

public class NetworkDevice {
	
	private String IPAddress;
	private String MACAddress;
	private int Id;
	
	public NetworkDevice(String iPAddress, String mACAddress, int id) {
		IPAddress = iPAddress;
		MACAddress = mACAddress;
		Id = id;
	}
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public String getMACAddress() {
		return MACAddress;
	}
	public void setMACAddress(String mACAddress) {
		MACAddress = mACAddress;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	
	
}
