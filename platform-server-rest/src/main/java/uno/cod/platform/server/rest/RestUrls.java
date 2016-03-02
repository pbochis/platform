package uno.cod.platform.server.rest;


public class RestUrls {
    public static final String CHALLENGES = "/challenges";
    public static final String CHALLENGES_ID = "/challenges/{id}";

    public static final String ORGANIZATIONS = "/organizations";
    public static final String ORGANIZATIONS_ID = "/organizations/{id}";

    public static final String ORGANIZATION_MEMBERS = "/organization/{id}/members";

    public static final String INVITE = "/invite";
    public static final String INVITE_AUTH_TOKEN = "/invite/auth/{token}";


    public static final String USERS = "/users";
    public static final String USER = "/user";
    public static final String USER_ORGANIZATION = "/user/organization";

    public static final String RESULTS = "/results";
    public static final String RESULTS_ID = "/results/{id}";
    public static final String RESULTS_ID_TASK_ID = "/results/{id}/task/{taskId}";

    public static final String TASKS = "/tasks";
    public static final String TASKS_ID = "/tasks/{id}";

    public static final String TEMPLATES_ID = "/templates/{id}";

    public static final String TASKS_ID_TESTS = "/tasks/{id}/tests";

    public static final String RESULTS_TASKS_SUBMISSIONS = "/results/{resultId}/tasks/{taskId}/submissions";

    public static final String SETUP = "/setup";

    public static final String ENDPOINTS = "/endpoints";

}
