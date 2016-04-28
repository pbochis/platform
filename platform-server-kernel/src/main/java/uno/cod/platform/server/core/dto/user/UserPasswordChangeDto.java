package uno.cod.platform.server.core.dto.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserPasswordChangeDto {
    @NotNull
    @Size(min = 6, max = 40)
    private String oldPassword;
    @NotNull
    @Size(min = 6, max = 40)
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
