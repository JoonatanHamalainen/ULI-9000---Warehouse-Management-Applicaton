package Ryhma7.ULI_9000.model;

import java.awt.Point;

public class Wall {
	
	private int coordinateX, coordinateY, storageID, wallID;
	
	public Wall() {
	}
	
	public Wall(int coordinateX, int coordinateY, int storageID) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.storageID = storageID;
	}
	
	public int getStorageID() {
		return storageID;
	}
	public int getWallID() {
		return wallID;
	}
	public Point getCellCoordinates() {
		return cellCoordinates;
	}
	private Point cellCoordinates;
	public int getCoordinateX() {
		return coordinateX;
	}
	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}
	public void setStorageID(int storageID) {
		this.storageID = storageID;
	}
	public void setWallID(int wallID) {
		this.wallID = wallID;
	}
	public void setCellCoordinates(Point cellCoordinates) {
		this.cellCoordinates = cellCoordinates;
	}
	public int getCoordinateY() {
		return coordinateY;
	}
	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}
}
