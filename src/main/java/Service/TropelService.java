package Service;

import DTO.TropelRequestDTO;
import DTO.TropelResponseDTO;
import Model.Tropel;
import Model.Sector;
import Model.Guardian;
import Repository.TropelRepository;
import Repository.SectorRepository;
import Repository.GuardianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TropelService {

    private final    TropelRepository tropelRepository;
    private final ModelMapper modelMapper;
    private final SectorRepository sectorRepository;
    private final GuardianRepository guardianRepository;

    public TropelService(TropelRepository tropelRepository, SectorRepository sectorRepository,
                         GuardianRepository guardianRepository, ModelMapper modelMapper) {
        this.tropelRepository = tropelRepository;
        this.sectorRepository = sectorRepository;
        this.guardianRepository = guardianRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public TropelResponseDTO createTropel(TropelRequestDTO tropelDTO) {

        if (tropelRepository.existsByName(tropelDTO.getName()))
            throw new ResponseStatusException(HttpStatus.CONFLICT,"mo");
        // 1. Buscar Sector por sectorId. Lanzar excepción 404 si no existe.
        Sector sector = sectorRepository.findSectorById(tropelDTO.getSectorId()).orElseThrow();
        // 2. Buscar Guardian por guardianId. Lanzar excepción 404 si no existe.
        Guardian guardian = guardianRepository.findGuardianById(tropelDTO.getGuardianId()).orElseThrow();
        // 3. Validar que sector.getCurrentLoad() < sector.getCapacity(). Lanzar 400 si está lleno.
        if (sector.getCurrentLoad() >= sector.getCapacity())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"no");
        // 4. Crear el Tropel con los valores iniciales.
        Tropel tropel = modelMapper.map(tropelDTO,Tropel.class);

        tropel.setSector(sector);
        tropel.setGuardian(guardian);
        tropel.setVitalState("ESTABLE");
        tropel.setEnergyLevel(80);
        tropel.setChaosIndex(10);
        tropel.setMutationStage(0);
        tropel.setCreatedAt(Instant.now());
        tropel.setUpdatedAt(Instant.now());

        // 5. Incrementar sector.currentLoad en 1 y guardar el sector[cite: 1].
        sector.setCurrentLoad(sector.getCurrentLoad()+1);
        sectorRepository.save(newSector);
        // 6. Guardar el Tropel y retornarlo[cite: 1].
        Tropel Savedtropel = tropelRepository.save(trope);
        TropelResponseDTO tropelResponseDTO = modelMapper.map(Savedtropel, TropelResponseDTO.class);

        tropelResponseDTO.setSectorId(sector.getId());
        tropelResponseDTO.setSectorCode(sector.getSectorCode());
        tropelResponseDTO.setGuardianId(guardian.getId());
        tropelResponseDTO.setGuardianName(guardian.getDisplayName());

        return tropelResponseDTO;
    }
    //get
    @Transactional(readOnly = true)
    public TropelResponseDTO getTropelByI(Long tropelId) {
        Tropel tropel = tropelRepository.findById(tropelId).orElseThrow();
        TropelResponseDTO tropelResponseDTO = modelMapper.map(tropel,TropelResponseDTO.class);

        tropelResponseDTO.setSectorId(tropel.getSector().getId());
        tropelResponseDTO.setSectorCode(tropel.getSector().getSectorCode());
        tropelResponseDTO.setGuardianId(tropel.getGuardian().getId());
        tropelResponseDTO.setGuardianName(tropel.getGuardian().getDisplayName());
        return tropelResponseDTO;
    }

    @Transactional(readOnly = true)
    public Page<TropelResponseDTO> getAllTropels(String species, String vitalState,
                                                 Long sectorId, Long guardianId, Pageable pageable) {
        // falta    JpaSpecificationExecutor
    }
}