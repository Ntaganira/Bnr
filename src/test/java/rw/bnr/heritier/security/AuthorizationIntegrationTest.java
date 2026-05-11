/**
 * --------------------------------------------------------------------
 * Project      : Bank Licensing Portal
 * File         : AuthorizationIntegrationTest.java
 * Author       : Heritier Ntaganira
 * Created Date : 2026-05-11
 * Description  : Tests role-based authorization rules
 * --------------------------------------------------------------------
 */

package rw.bnr.heritier.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import rw.bnr.heritier.application.model.LicenseApplication;
import rw.bnr.heritier.application.service.ApplicationWorkflowService;
import rw.bnr.heritier.user.model.User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorizationIntegrationTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private ApplicationWorkflowService workflowService;

        @Test
        @WithMockUser(username = "applicant@nbr.rw", roles = { "APPLICANT" })
        void applicantCannotApproveApplication()
                        throws Exception {

                mockMvc.perform(
                                post("/api/applications/1/approve"))
                                .andExpect(status().isForbidden());
        }

        @Test
        @WithMockUser(username = "reviewer@nbr.rw", roles = { "REVIEWER" })
        void reviewerCannotApproveApplication()
                        throws Exception {

                mockMvc.perform(
                                post("/api/applications/1/approve"))
                                .andExpect(status().isForbidden());
        }

        @Test
        @WithMockUser(username = "approver@nbr.rw", roles = { "APPROVER" })
        void approverCanAccessApprovalEndpoint()
                        throws Exception {

                LicenseApplication application = LicenseApplication.builder()
                                .id(1L)
                                .build();

                when(workflowService.approve(
                                eq(1L),
                                any(User.class))).thenReturn(application);

                mockMvc.perform(
                                post("/api/applications/1/approve"))
                                .andExpect(status().isOk());
        }

}