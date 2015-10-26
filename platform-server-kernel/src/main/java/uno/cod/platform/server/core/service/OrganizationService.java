package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.Organization;
import uno.cod.platform.server.core.domain.OrganizationMember;
import uno.cod.platform.server.core.domain.OrganizationMemberKey;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.dto.organization.OrganizationCreateDto;
import uno.cod.platform.server.core.repository.OrganizationMemberRepository;
import uno.cod.platform.server.core.repository.OrganizationRepository;
import uno.cod.platform.server.core.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrganizationService {
    private OrganizationRepository organizationRepository;
    private OrganizationMemberRepository organizationMemberRepository;
    private UserRepository userRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository,
                               OrganizationMemberRepository organizationMemberRepository,
                               UserRepository userRepository) {
        this.userRepository = userRepository;
        this.organizationMemberRepository = organizationMemberRepository;
        this.organizationRepository = organizationRepository;
    }

    public void createFromDto(OrganizationCreateDto dto, String owner) {
        Organization organization = new Organization();
        organization.setNick(dto.getNick());
        organization.setName(dto.getName());
        organization = organizationRepository.save(organization);

        User user = userRepository.findByUsername(owner);

        OrganizationMemberKey organizationMemberKey = new OrganizationMemberKey();
        organizationMemberKey.setOrganization(organization);
        organizationMemberKey.setUser(user);

        OrganizationMember organizationMember = new OrganizationMember();
        organizationMember.setKey(organizationMemberKey);
        organizationMember.setAdmin(true);

        organizationMemberRepository.save(organizationMember);
    }
}
