package hr.foi.air1719.core.adapter;

import hr.foi.air1719.database.entities.GpsLocation;

/**
 * Created by abenkovic on 11/26/17.
 */

public interface IDataAdapter {

    public void getLocation();

    public void saveLocation(GpsLocation location);
}
