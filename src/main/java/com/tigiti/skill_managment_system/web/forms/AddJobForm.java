package com.tigiti.skill_managment_system.web.forms;

import com.tigiti.skill_managment_system.enteties.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data @AllArgsConstructor
@NoArgsConstructor
public class AddJobForm {
    private Job job;
    private String userNameEnterpriseAdmin;
}
