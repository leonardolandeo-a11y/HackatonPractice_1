package Service;

import Model.Tropel;
import Repository.TropelRepository;
import Repository.TropelSignalRepository;
import org.springframework.stereotype.Service;

@Service
public class TropelSignalService {
    private final TropelSignalRepository tropelSignalRepository;
    private final ModelMapper modelMapper;
    private final GuardianRepository guardianRepository;
    private final TropelRepository tropelRepository;
    private final SectorRepository sectorRepository;

    public TropelSignalService(TropelSignalRepository tropelSignalRepository, ModelMapper modelMapper,
                               GuardianRepository guardianRepository) {
        this.tropelSignalRepository = tropelSignalRepository;
        this.guardianRepository = guardianRepository;
        this.modelMapper = modelMapper;
    }
    //post (crear señal)
    // get por id
    // get all signals
}
