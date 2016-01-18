package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.Organization;
import uno.cod.platform.server.core.domain.OrganizationMember;
import uno.cod.platform.server.core.domain.OrganizationMemberKey;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.dto.organization.member.OrganizationMemberCreateDto;
import uno.cod.platform.server.core.exception.ResourceConflictException;
import uno.cod.platform.server.core.repository.OrganizationMemberRepository;
import uno.cod.platform.server.core.repository.OrganizationRepository;
import uno.cod.platform.server.core.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrganizationMemberService {
    private final OrganizationMemberRepository repository;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationMemberService(OrganizationMemberRepository repository, UserRepository userRepository, OrganizationRepository organizationRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
    }

    public void save(OrganizationMemberCreateDto dto, Long organizationId) {
        User user = userRepository.findOne(dto.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("user.invalid");
        }
        Organization organization = organizationRepository.findOne(organizationId);
        if (organization == null) {
            throw new IllegalArgumentException("organization.invalid");
        }
        OrganizationMemberKey key = new OrganizationMemberKey();
        key.setUser(user);
        key.setOrganization(organization);
        if(repository.findOne(key)!=null){
            throw new ResourceConflictException("organization.member.exists");
        }
        OrganizationMember member = new OrganizationMember();
        member.setKey(key);
        member.setAdmin(dto.isAdmin());
        repository.save(member);
        user.addOrganizationMember(member);
        organization.addOrganizationMember(member);
    }

    public void delete(OrganizationMemberCreateDto dto, Long organizationId) {
        User user = userRepository.findOne(dto.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("user.invalid");
        }
        Organization organization = organizationRepository.findOne(organizationId);
        if (organization == null) {
            throw new IllegalArgumentException("organization.invalid");
        }
        OrganizationMemberKey key = new OrganizationMemberKey();
        key.setUser(user);
        key.setOrganization(organization);
        repository.delete(key);
    }
}