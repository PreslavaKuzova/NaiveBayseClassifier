public enum Feature {
    HANDICAPPED_INFANT(0),
    WATER_PROJECT_COST_SHARING(1),
    ADOPTION_OF_THE_BUDGET_RESOLUTION(2),
    PHYSICIAN_FEE_FREEZE(3),
    EL_SALVADOR_AI(4),
    RELIGIOUS_GROUPS_IN_SCHOOL(5),
    ANTI_SATELLITE_TEST_BA(6),
    AID_TO_NICARAGUAN_CONTRA(7),
    MX_MISSILE(8),
    IMMIGRATION(9),
    SYNFUELS_CORPORATION_CUTBACK(10),
    EDUCATION_SPENDING(11),
    SUPERFUND_RIGHT_TO_SUE(12),
    CRIME(13),
    DUTY_FREE_EXPORTS(14),
    EXPORT_ADMINISTRATION_ACT_SOUTH_AFRICA(15);

    private final int position;

    Feature(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
