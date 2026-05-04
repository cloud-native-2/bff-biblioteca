package cl.kemolinaj.bff.biblioteca.service;

import cl.kemolinaj.bff.biblioteca.dtos.event.CorreoRqDto;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.BinaryData;
import com.azure.messaging.eventgrid.EventGridEvent;
import com.azure.messaging.eventgrid.EventGridPublisherClient;
import com.azure.messaging.eventgrid.EventGridPublisherClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service("eventGridService")
public class EventGridService {
    @Value("${event.grid.topic-endpoint}")
    private String eventGridTopicEndpoint;
    @Value("${event.grid.key}")
    private String eventGridKey;
    @Value("${event.grid.tipo.evento.envio-correo}")
    private String eventTipoEnvioCorreo;
    @Value("${event.grid.tipo.evento.generar-comprobante}")
    private String eventTipoEnvioComprobante;

    public void enviarCorreoEvent(CorreoRqDto correoRqDto) {
        try {
            EventGridPublisherClient<EventGridEvent> client = new EventGridPublisherClientBuilder()
                    .endpoint(eventGridTopicEndpoint)
                    .credential(new AzureKeyCredential(eventGridKey))
                    .buildEventGridEventPublisherClient();

            EventGridEvent event = new EventGridEvent("/envio/correo",
                    eventTipoEnvioCorreo, BinaryData.fromObject(correoRqDto), "0.1");

            client.sendEvent(event);
        } catch (Exception e) {
            log.error("Error al enviar el evento: ", e);
        }
    }

    public void generarComprobante() {
        try {
            EventGridPublisherClient<EventGridEvent> client = new EventGridPublisherClientBuilder()
                    .endpoint(eventGridTopicEndpoint)
                    .credential(new AzureKeyCredential(eventGridKey))
                    .buildEventGridEventPublisherClient();

            EventGridEvent event = new EventGridEvent("/generar/comprobante",
                    eventTipoEnvioComprobante, BinaryData.fromObject(null), "0.1");

            client.sendEvent(event);
        } catch (Exception e) {
            log.error("Error al enviar el evento: ", e);
        }
    }
}
