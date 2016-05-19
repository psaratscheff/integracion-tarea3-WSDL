/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.saratscheff.integracion.t3;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import net.webservicex.Airport;
import net.webservicex.AirportSoap;
import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

/**
 *
 * @author pedro
 */
@WebService(serviceName = "CiudadesYAeropuertosPorPais")
public class CiudadesYAeropuertosPorPais {

    /**
     * This is a sample web service operation
     * @param pais
     * @return best answer ever
     */
    @WebMethod(operationName = "obtenerCiudadesYAeropuertos")
    public String[] obtenerCiudadesYAeropuertos(@WebParam(name = "pais") String pais) {
        String[] result = new String[2];
        
        GlobalWeather gw = new GlobalWeather(); //Obtengo el servicio "completo"/cliente
        GlobalWeatherSoap gwSoap = gw.getGlobalWeatherSoap(); // Obtengo la instancia del cliente (SOAP)
        
        String cities = gwSoap.getCitiesByCountry(pais); // Utilizo una función del soap, las funciones ya existen en gwSoap
        
        result[0] = cities;
        
        Airport ap = new Airport(); //Obtengo el servicio "completo"/cliente
        AirportSoap apSoap = ap.getAirportSoap(); // Obtengo la instancia del cliente (SOAP)
        
        String airports = apSoap.getAirportInformationByCountry(pais); // Utilizo una función del soap, las funciones ya existen en apSoap
        //El llamado a cities es síncrono!!
        
        result[1] = airports;
        return result;
    }
}
