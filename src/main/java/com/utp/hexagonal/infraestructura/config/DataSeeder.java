package com.utp.hexagonal.infraestructura.config;

import com.utp.hexagonal.infraestructura.entity.DoctorEntity;
import com.utp.hexagonal.infraestructura.entity.EspecialidadEntity;
import com.utp.hexagonal.infraestructura.entity.UsuarioEntity;
import com.utp.hexagonal.infraestructura.repository.DoctorJPARepository;
import com.utp.hexagonal.infraestructura.repository.EspecialidadJPARepository;
import com.utp.hexagonal.infraestructura.repository.UsuarioJPARepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    private final EspecialidadJPARepository especialidadRepo;
    private final DoctorJPARepository doctorRepo;
    private final UsuarioJPARepository usuarioRepo;

    @PostConstruct
    public void seedData() {
        if (especialidadRepo.count() == 0) {
            List<EspecialidadEntity> especialidades = Arrays.asList(
                    new EspecialidadEntity(null, "Medicina General"),
                    new EspecialidadEntity(null, "Pediatría"),
                    new EspecialidadEntity(null, "Cardiología"),
                    new EspecialidadEntity(null, "Dermatología"),
                    new EspecialidadEntity(null, "Ginecología")
            );
            especialidadRepo.saveAll(especialidades);
        }

        if (doctorRepo.count() == 0) {
            List<EspecialidadEntity> especialidades = especialidadRepo.findAll();

            doctorRepo.saveAll(Arrays.asList(
                    new DoctorEntity(null, "Dr. Ana Torres", especialidades.get(0)),
                    new DoctorEntity(null, "Dr. Luis Pérez", especialidades.get(0)),
                    new DoctorEntity(null, "Dra. Marta Ramírez", especialidades.get(1)),
                    new DoctorEntity(null, "Dr. Jorge Salinas", especialidades.get(1)),
                    new DoctorEntity(null, "Dr. Mario Cárdenas", especialidades.get(2)),
                    new DoctorEntity(null, "Dra. Elena Chávez", especialidades.get(2)),
                    new DoctorEntity(null, "Dr. Oscar Benites", especialidades.get(3)),
                    new DoctorEntity(null, "Dra. Camila Luján", especialidades.get(3)),
                    new DoctorEntity(null, "Dr. Juan Gutiérrez", especialidades.get(4)),
                    new DoctorEntity(null, "Dra. Silvia Núñez", especialidades.get(4)),
                    new DoctorEntity(null, "Dr. José Robles", especialidades.get(4)),
                    new DoctorEntity(null, "Dra. Martina Rumiche", especialidades.get(4))
            ));
        }

        if (usuarioRepo.count() == 0) {
            UsuarioEntity admin = new UsuarioEntity();
            admin.setUsername("admin1");
            admin.setPassword("admin123"); // puedes cifrarlo después
            admin.setRol("ADMIN");
            admin.setPaciente(null); // no asociado a paciente
            usuarioRepo.save(admin);
        }
    }
}
