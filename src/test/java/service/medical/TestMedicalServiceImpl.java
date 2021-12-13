package service.medical;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.patient.entity.*;
import ru.netology.patient.repository.PatientInfoFileRepository;
import ru.netology.patient.service.alert.SendAlertServiceImpl;
import ru.netology.patient.service.medical.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TestMedicalServiceImpl {
    String patientId = "id";
    PatientInfo patientInfo = new PatientInfo(patientId,"Ivan",
            "Ivanov", LocalDate.of(1990, 5, 10),
            new HealthInfo(new BigDecimal("36.6"),
                    new BloodPressure(110, 65)));

    @Test
    public void check_GetWarningIfBloodPressureIsNotOk(){
        PatientInfoFileRepository patientInfoFileRepository = Mockito.mock(PatientInfoFileRepository.class);
        SendAlertServiceImpl service = Mockito.mock(SendAlertServiceImpl.class);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoFileRepository, service);
        Mockito.when(patientInfoFileRepository.getById(patientId))
                .thenReturn(patientInfo);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        String expected = String.format("Warning, patient with id: %s, need help", patientId);
        medicalService.checkBloodPressure(patientId, new BloodPressure(130, 90));
        Mockito.verify(service, Mockito.only()).send(captor.capture());
        Assertions.assertEquals(expected, captor.getValue());
    }

}
