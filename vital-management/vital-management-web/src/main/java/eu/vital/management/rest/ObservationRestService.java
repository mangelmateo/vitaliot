package eu.vital.management.rest;

import com.fasterxml.jackson.databind.JsonNode;
import eu.vital.management.service.ObservationService;
import eu.vital.management.service.SensorDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/observation")
@RequestScoped
public class ObservationRestService {

	@Inject
	SensorDAO sensorDAO;

	@Inject
	ObservationService observationService;

	@Inject
	private Logger log;


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(JsonNode query) throws Exception {
		/*
		{
            "@context": "http://vital-iot.org/contexts/query.jsonld",
  			"sensor": "http://example.com/sensor/2",
  			"property": "http://lsm.deri.ie/OpenIot/Temperature"
  			"from": "2014-11-17T09:00:00+02:00",
  			"to": "2014-11-17T11:00:00+02:00"
        }
		*/

		String sensorId = query.get("sensor").asText();
		String observationType = query.get("property").asText();

		return Response.ok(observationService.fetchObservation(sensorId, observationType)).build();
	}

	@POST
	@Path("/performance")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerformance(JsonNode query) throws Exception {
		/*
		{
            "@context": "http://vital-iot.org/contexts/query.jsonld",
  			"sensor": "http://example.com/sensor/2",
  			"property": "http://lsm.deri.ie/OpenIot/Temperature"
        }
		*/

		String sensorId = query.get("sensor").asText();
		String observationType = query.get("property").asText();

		return Response.ok(observationService.fetchRandomObservation(sensorId, observationType)).build();
	}

}
