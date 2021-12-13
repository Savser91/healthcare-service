package service.medical;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import ru.netology.patient.entity.*;
import ru.netology.patient.repository.PatientInfoFileRepository;
import ru.netology.patient.service.alert.SendAlertServiceImpl;
import ru.netology.patient.service.medical.MedicalServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TestMedicalServiceImpl {
    PatientInfoFileRepository patientInfoFileRepository;
    SendAlertServiceImpl service;
    MedicalServiceImpl medicalService;
    String patientId = "id";
    PatientInfo patientInfo = new PatientInfo(patientId,"Ivan",
            "Ivanov", LocalDate.of(1990, 5, 10),
            new HealthInfo(new BigDecimal("36.6"),
                    new BloodPressure(110, 65)));
    @BeforeEach
    public void setMock() {
        patientInfoFileRepository = Mockito.mock(PatientInfoFileRepository.class);
        service = Mockito.mock(SendAlertServiceImpl.class);
        medicalService = new MedicalServiceImpl(patientInfoFileRepository, service);
    }

}
