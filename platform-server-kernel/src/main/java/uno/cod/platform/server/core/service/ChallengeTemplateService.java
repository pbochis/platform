package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.ChallengeTemplate;
import uno.cod.platform.server.core.domain.Endpoint;
import uno.cod.platform.server.core.domain.Organization;
import uno.cod.platform.server.core.dto.challenge.template.ChallengeTemplateCreateDto;
import uno.cod.platform.server.core.dto.challenge.template.ChallengeTemplateShowDto;
import uno.cod.platform.server.core.mapper.ChallengeMapper;
import uno.cod.platform.server.core.repository.ChallengeTemplateRepository;
import uno.cod.platform.server.core.repository.EndpointRepository;
import uno.cod.platform.server.core.repository.OrganizationRepository;
import uno.cod.platform.server.core.repository.TaskRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChallengeTemplateService {
    private final ChallengeTemplateRepository repository;
    private final TaskRepository taskRepository;
    private final OrganizationRepository organizationRepository;
    private final EndpointRepository endpointRepository;

    @Autowired
    public ChallengeTemplateService(ChallengeTemplateRepository repository, TaskRepository taskRepository, OrganizationRepository organizationRepository, EndpointRepository endpointRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
        this.organizationRepository = organizationRepository;
        this.endpointRepository = endpointRepository;
    }

    public Long save(ChallengeTemplateCreateDto dto) {
        Organization organization = organizationRepository.findOne(dto.getOrganizationId());
        if(organization==null){
            throw new IllegalArgumentException("organization.invalid");
        }
        Endpoint endpoint = endpointRepository.findOne(dto.getEndpointId());
        if (endpoint == null) {
            throw new IllegalArgumentException("endpoint.invalid");
        }
        ChallengeTemplate challengeTemplate = new ChallengeTemplate();
        challengeTemplate.setName(dto.getName());
        challengeTemplate.setDescription(dto.getDescription());
        challengeTemplate.setInstructions(dto.getInstructions());
        challengeTemplate.setDuration(dto.getDuration());
        for (Long taskId : dto.getTasks()) {
            challengeTemplate.addTask(taskRepository.getOne(taskId));
        }
        organization.addChallenge(challengeTemplate);
        endpoint.addChallenge(challengeTemplate);

        return repository.save(challengeTemplate).getId();
    }

    public ChallengeTemplateShowDto findById(Long id) {
        return ChallengeMapper.map(repository.findOneWithEndpointAndTasks(id));
    }

    public List<ChallengeTemplateShowDto> findAll(Long organizationId) {
        return ChallengeMapper.map(repository.findAllWithTasks(organizationId));
    }
}
