package Ryhma7.ULI_9000.model;

import java.awt.Point;
/**
 * 
 * Class for model Wall
 *
 */
public class Wall {
	
	private int coordinateX, coordinateY, storageID, wallID;
	/**
	 * Empty constructor
	 */
	public Wall() {
	}
	/**
	 * Constructor creates a new wall with given parameters
	 * 
	 * @param coordinateX describes the X coordinate being set
	 * @param coordinateY describes the Y coordinate being set
	 * @param storageID describes the storageID being set
	 */
	/**
	 * @param coordinateX sets X coordinate of Wall-object
	 * @param coordinateY sets Y coordinate of Wall-object
	 * @param storageID sets storageID of Wall-object
	 */
	public Wall(int coordinateX, int coordinateY, int storageID) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.storageID = storageID;
	}
	/**
	 * 
	 * @return returns storageID of Wall-object
	 */
	public int getStorageID() {
		return storageID;
	}
	/**
	 * 
	 * @return returns wallID of Wall-object
	 */
	public int getWallID() {
		return wallID;
	}
	/**
	 * 
	 * @return returns cellCoordinates of Wall-object
	 */
	public Point getCellCoordinates() {
		return cellCoordinates;
	}
	private Point cellCoordinates;
	/**
	 * 
	 * @return returns X coordinate of Wall-object
	 */
	public int getCoordinateX() {
		return coordinateX;
	}
	/**
	 * 
	 * @param coordinateX sets X coordinate of Wall-object
	 */
	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}
	/**
	 * 
	 * @param storageID sets storageID of Wall-object
	 */
	public void setStorageID(int storageID) {
		this.storageID = storageID;
	}
	/**
	 * 
	 * @param wallID sets WallID of Wall-object
	 */
	public void setWallID(int wallID) {
		this.wallID = wallID;
	}
	/**
	 * 
	 * @param cellCoordinates sets Cell coordinates of Wall-object
	 */
	public void setCellCoordinates(Point cellCoordinates) {
		this.cellCoordinates = cellCoordinates;
	}
	/**
	 * 
	 * @return returns Y coordinate of Wall-object
	 */
	public int getCoordinateY() {
		return coordinateY;
	}
	/**
	 * 
	 * @param coordinateY sets Y coordinate of Wall-object
	 */
	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}
}
