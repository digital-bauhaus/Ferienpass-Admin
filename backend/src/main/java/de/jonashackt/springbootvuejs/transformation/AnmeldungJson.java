package de.jonashackt.springbootvuejs.transformation;


import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "base__family-name",
        "base__forename",
        "base__birthdate-day",
        "base__birthdate-month",
        "base__birthdate-year",
        "base__street-name",
        "base__house-number",
        "base__zip-code",
        "base__residence",
        "base__phone-number",
        "projects__id-1",
        "projects__id-2",
        "projects__id-3",
        "projects__id-4",
        "projects__id-5",
        "projects__id-6",
        "projects__id-7",
        "projects__id-8",
        "projects__id-9",
        "projects__id-10",
        "projects__id-11",
        "projects__id-12",
        "projects__id-13",
        "projects__id-14",
        "projects__id-15",
        "projects__id-16",
        "projects__id-17",
        "projects__id-18",
        "projects__id-19",
        "projects__id-20",
        "projects__id-21",
        "conditions__allergies-0",
        "conditions__allergies-1",
        "conditions__allergies-2",
        "conditions__allergies-3",
        "conditions__allergies-4",
        "conditions__diseases-0",
        "conditions__diseases-1",
        "conditions__diseases-2",
        "conditions__diseases-3",
        "conditions__diseases-4",
        "conditions__heat-sensitivity",
        "conditions__medication-0",
        "conditions__vegetarian",
        "conditions__lactose-intolerance",
        "conditions__egg-intolerance",
        "conditions__nutrition-0",
        "conditions__child-treatment-allowed",
        "conditions__health-insurance",
        "conditions__emergency-name",
        "conditions__emergency-address",
        "conditions__emergency-phone-number",
        "conditions__family-doctor-name",
        "conditions__family-doctor-address",
        "conditions__family-doctor-phone-number",
        "disabilities__disability-existent",
        "disabilities__mark-ag",
        "disabilities__mark-h",
        "disabilities__mark-bl",
        "disabilities__mark-gl",
        "disabilities__mark-b",
        "disabilities__mark-g",
        "disabilities__mark-tbl",
        "disabilities__wheelchair",
        "disabilities__additional-tools",
        "disabilities__token-available",
        "disabilities__companion-required",
        "disabilities__companion-for-nursing",
        "disabilities__companion-for-health-care",
        "disabilities__companion-for-mobility",
        "disabilities__companion-for-orientation",
        "disabilities__companion-social",
        "disabilities__affected-senses-0",
        "disabilities__companion-additional-notes",
        "disabilities__companion-help-finding-required",
        "disabilities__companion-usual-service",
        "disabilities__companion-cost-takeover",
        "declaration__going-home-alone-allowed",
        "declaration__horse-riding-allowed",
        "declaration__swimming-allowed",
        "declaration__swimming-badge",
        "privacy-policy__confirmation"
})
public class AnmeldungJson {

    @JsonProperty("base__family-name")
    private String baseFamilyName;
    @JsonProperty("base__forename")
    private String baseForename;
    @JsonProperty("base__birthdate-day")
    private String baseBirthdateDay;
    @JsonProperty("base__birthdate-month")
    private String baseBirthdateMonth;
    @JsonProperty("base__birthdate-year")
    private String baseBirthdateYear;
    @JsonProperty("base__street-name")
    private String baseStreetName;
    @JsonProperty("base__house-number")
    private String baseHouseNumber;
    @JsonProperty("base__zip-code")
    private String baseZipCode;
    @JsonProperty("base__residence")
    private String baseResidence;
    @JsonProperty("base__phone-number")
    private String basePhoneNumber;
    @JsonProperty("projects__id-1")
    private Boolean projectsId1;
    @JsonProperty("projects__id-2")
    private Boolean projectsId2;
    @JsonProperty("projects__id-3")
    private Boolean projectsId3;
    @JsonProperty("projects__id-4")
    private Boolean projectsId4;
    @JsonProperty("projects__id-5")
    private Boolean projectsId5;
    @JsonProperty("projects__id-6")
    private Boolean projectsId6;
    @JsonProperty("projects__id-7")
    private Boolean projectsId7;
    @JsonProperty("projects__id-8")
    private Boolean projectsId8;
    @JsonProperty("projects__id-9")
    private Boolean projectsId9;
    @JsonProperty("projects__id-10")
    private Boolean projectsId10;
    @JsonProperty("projects__id-11")
    private Boolean projectsId11;
    @JsonProperty("projects__id-12")
    private Boolean projectsId12;
    @JsonProperty("projects__id-13")
    private Boolean projectsId13;
    @JsonProperty("projects__id-14")
    private Boolean projectsId14;
    @JsonProperty("projects__id-15")
    private Boolean projectsId15;
    @JsonProperty("projects__id-16")
    private Boolean projectsId16;
    @JsonProperty("projects__id-17")
    private Boolean projectsId17;
    @JsonProperty("projects__id-18")
    private Boolean projectsId18;
    @JsonProperty("projects__id-19")
    private Boolean projectsId19;
    @JsonProperty("projects__id-20")
    private Boolean projectsId20;
    @JsonProperty("projects__id-21")
    private Boolean projectsId21;
    @JsonProperty("conditions__allergies-0")
    private String conditionsAllergies0;
    @JsonProperty("conditions__allergies-1")
    private String conditionsAllergies1;
    @JsonProperty("conditions__allergies-2")
    private String conditionsAllergies2;
    @JsonProperty("conditions__allergies-3")
    private String conditionsAllergies3;
    @JsonProperty("conditions__allergies-4")
    private String conditionsAllergies4;
    @JsonProperty("conditions__diseases-0")
    private String conditionsDiseases0;
    @JsonProperty("conditions__diseases-1")
    private String conditionsDiseases1;
    @JsonProperty("conditions__diseases-2")
    private String conditionsDiseases2;
    @JsonProperty("conditions__diseases-3")
    private String conditionsDiseases3;
    @JsonProperty("conditions__diseases-4")
    private String conditionsDiseases4;
    @JsonProperty("conditions__heat-sensitivity")
    private Boolean conditionsHeatSensitivity;
    @JsonProperty("conditions__medication-0")
    private String conditionsMedication0;
    @JsonProperty("conditions__vegetarian")
    private Boolean conditionsVegetarian;
    @JsonProperty("conditions__lactose-intolerance")
    private Boolean conditionsLactoseIntolerance;
    @JsonProperty("conditions__egg-intolerance")
    private Boolean conditionsEggIntolerance;
    @JsonProperty("conditions__nutrition-0")
    private String conditionsNutrition0;
    @JsonProperty("conditions__child-treatment-allowed")
    private String conditionsChildTreatmentAllowed;
    @JsonProperty("conditions__health-insurance")
    private String conditionsHealthInsurance;
    @JsonProperty("conditions__emergency-name")
    private String conditionsEmergencyName;
    @JsonProperty("conditions__emergency-address")
    private String conditionsEmergencyAddress;
    @JsonProperty("conditions__emergency-phone-number")
    private String conditionsEmergencyPhoneNumber;
    @JsonProperty("conditions__family-doctor-name")
    private String conditionsFamilyDoctorName;
    @JsonProperty("conditions__family-doctor-address")
    private String conditionsFamilyDoctorAddress;
    @JsonProperty("conditions__family-doctor-phone-number")
    private String conditionsFamilyDoctorPhoneNumber;
    @JsonProperty("disabilities__disability-existent")
    private Boolean disabilitiesDisabilityExistent;
    @JsonProperty("disabilities__mark-ag")
    private Boolean disabilitiesMarkAg;
    @JsonProperty("disabilities__mark-h")
    private Boolean disabilitiesMarkH;
    @JsonProperty("disabilities__mark-bl")
    private Boolean disabilitiesMarkBl;
    @JsonProperty("disabilities__mark-gl")
    private Boolean disabilitiesMarkGl;
    @JsonProperty("disabilities__mark-b")
    private Boolean disabilitiesMarkB;
    @JsonProperty("disabilities__mark-g")
    private Boolean disabilitiesMarkG;
    @JsonProperty("disabilities__mark-tbl")
    private Boolean disabilitiesMarkTbl;
    @JsonProperty("disabilities__wheelchair")
    private Boolean disabilitiesWheelchair;
    @JsonProperty("disabilities__additional-tools")
    private String disabilitiesAdditionalTools;
    @JsonProperty("disabilities__token-available")
    private Boolean disabilitiesTokenAvailable;
    @JsonProperty("disabilities__companion-required")
    private Boolean disabilitiesCompanionRequired;
    @JsonProperty("disabilities__companion-for-nursing")
    private Boolean disabilitiesCompanionForNursing;
    @JsonProperty("disabilities__companion-for-health-care")
    private Boolean disabilitiesCompanionForHealthCare;
    @JsonProperty("disabilities__companion-for-mobility")
    private Boolean disabilitiesCompanionForMobility;
    @JsonProperty("disabilities__companion-for-orientation")
    private Boolean disabilitiesCompanionForOrientation;
    @JsonProperty("disabilities__companion-social")
    private Boolean disabilitiesCompanionSocial;
    @JsonProperty("disabilities__affected-senses-0")
    private String disabilitiesAffectedSenses0;
    @JsonProperty("disabilities__companion-additional-notes")
    private String disabilitiesCompanionAdditionalNotes;
    @JsonProperty("disabilities__companion-help-finding-required")
    private Boolean disabilitiesCompanionHelpFindingRequired;
    @JsonProperty("disabilities__companion-usual-service")
    private String disabilitiesCompanionUsualService;
    @JsonProperty("disabilities__companion-cost-takeover")
    private Boolean disabilitiesCompanionCostTakeover;
    @JsonProperty("declaration__going-home-alone-allowed")
    private String declarationGoingHomeAloneAllowed;
    @JsonProperty("declaration__horse-riding-allowed")
    private String declarationHorseRidingAllowed;
    @JsonProperty("declaration__swimming-allowed")
    private String declarationSwimmingAllowed;
    @JsonProperty("declaration__swimming-badge")
    private String declarationSwimmingBadge;
    @JsonProperty("privacy-policy__confirmation")
    private Boolean privacyPolicyConfirmation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("base__family-name")
    public String getBaseFamilyName() {
        return baseFamilyName;
    }

    @JsonProperty("base__family-name")
    public void setBaseFamilyName(String baseFamilyName) {
        this.baseFamilyName = baseFamilyName;
    }

    @JsonProperty("base__forename")
    public String getBaseForename() {
        return baseForename;
    }

    @JsonProperty("base__forename")
    public void setBaseForename(String baseForename) {
        this.baseForename = baseForename;
    }

    @JsonProperty("base__birthdate-day")
    public String getBaseBirthdateDay() {
        return baseBirthdateDay;
    }

    @JsonProperty("base__birthdate-day")
    public void setBaseBirthdateDay(String baseBirthdateDay) {
        this.baseBirthdateDay = baseBirthdateDay;
    }

    @JsonProperty("base__birthdate-month")
    public String getBaseBirthdateMonth() {
        return baseBirthdateMonth;
    }

    @JsonProperty("base__birthdate-month")
    public void setBaseBirthdateMonth(String baseBirthdateMonth) {
        this.baseBirthdateMonth = baseBirthdateMonth;
    }

    @JsonProperty("base__birthdate-year")
    public String getBaseBirthdateYear() {
        return baseBirthdateYear;
    }

    @JsonProperty("base__birthdate-year")
    public void setBaseBirthdateYear(String baseBirthdateYear) {
        this.baseBirthdateYear = baseBirthdateYear;
    }

    @JsonProperty("base__street-name")
    public String getBaseStreetName() {
        return baseStreetName;
    }

    @JsonProperty("base__street-name")
    public void setBaseStreetName(String baseStreetName) {
        this.baseStreetName = baseStreetName;
    }

    @JsonProperty("base__house-number")
    public String getBaseHouseNumber() {
        return baseHouseNumber;
    }

    @JsonProperty("base__house-number")
    public void setBaseHouseNumber(String baseHouseNumber) {
        this.baseHouseNumber = baseHouseNumber;
    }

    @JsonProperty("base__zip-code")
    public String getBaseZipCode() {
        return baseZipCode;
    }

    @JsonProperty("base__zip-code")
    public void setBaseZipCode(String baseZipCode) {
        this.baseZipCode = baseZipCode;
    }

    @JsonProperty("base__residence")
    public String getBaseResidence() {
        return baseResidence;
    }

    @JsonProperty("base__residence")
    public void setBaseResidence(String baseResidence) {
        this.baseResidence = baseResidence;
    }

    @JsonProperty("base__phone-number")
    public String getBasePhoneNumber() {
        return basePhoneNumber;
    }

    @JsonProperty("base__phone-number")
    public void setBasePhoneNumber(String basePhoneNumber) {
        this.basePhoneNumber = basePhoneNumber;
    }

    @JsonProperty("projects__id-1")
    public Boolean getProjectsId1() {
        return projectsId1;
    }

    @JsonProperty("projects__id-1")
    public void setProjectsId1(Boolean projectsId1) {
        this.projectsId1 = projectsId1;
    }

    @JsonProperty("projects__id-2")
    public Boolean getProjectsId2() {
        return projectsId2;
    }

    @JsonProperty("projects__id-2")
    public void setProjectsId2(Boolean projectsId2) {
        this.projectsId2 = projectsId2;
    }

    @JsonProperty("projects__id-3")
    public Boolean getProjectsId3() {
        return projectsId3;
    }

    @JsonProperty("projects__id-3")
    public void setProjectsId3(Boolean projectsId3) {
        this.projectsId3 = projectsId3;
    }

    @JsonProperty("projects__id-4")
    public Boolean getProjectsId4() {
        return projectsId4;
    }

    @JsonProperty("projects__id-4")
    public void setProjectsId4(Boolean projectsId4) {
        this.projectsId4 = projectsId4;
    }

    @JsonProperty("projects__id-5")
    public Boolean getProjectsId5() {
        return projectsId5;
    }

    @JsonProperty("projects__id-5")
    public void setProjectsId5(Boolean projectsId5) {
        this.projectsId5 = projectsId5;
    }

    @JsonProperty("projects__id-6")
    public Boolean getProjectsId6() {
        return projectsId6;
    }

    @JsonProperty("projects__id-6")
    public void setProjectsId6(Boolean projectsId6) {
        this.projectsId6 = projectsId6;
    }

    @JsonProperty("projects__id-7")
    public Boolean getProjectsId7() {
        return projectsId7;
    }

    @JsonProperty("projects__id-7")
    public void setProjectsId7(Boolean projectsId7) {
        this.projectsId7 = projectsId7;
    }

    @JsonProperty("projects__id-8")
    public Boolean getProjectsId8() {
        return projectsId8;
    }

    @JsonProperty("projects__id-8")
    public void setProjectsId8(Boolean projectsId8) {
        this.projectsId8 = projectsId8;
    }

    @JsonProperty("projects__id-9")
    public Boolean getProjectsId9() {
        return projectsId9;
    }

    @JsonProperty("projects__id-9")
    public void setProjectsId9(Boolean projectsId9) {
        this.projectsId9 = projectsId9;
    }

    @JsonProperty("projects__id-10")
    public Boolean getProjectsId10() {
        return projectsId10;
    }

    @JsonProperty("projects__id-10")
    public void setProjectsId10(Boolean projectsId10) {
        this.projectsId10 = projectsId10;
    }

    @JsonProperty("projects__id-11")
    public Boolean getProjectsId11() {
        return projectsId11;
    }

    @JsonProperty("projects__id-11")
    public void setProjectsId11(Boolean projectsId11) {
        this.projectsId11 = projectsId11;
    }

    @JsonProperty("projects__id-12")
    public Boolean getProjectsId12() {
        return projectsId12;
    }

    @JsonProperty("projects__id-12")
    public void setProjectsId12(Boolean projectsId12) {
        this.projectsId12 = projectsId12;
    }

    @JsonProperty("projects__id-13")
    public Boolean getProjectsId13() {
        return projectsId13;
    }

    @JsonProperty("projects__id-13")
    public void setProjectsId13(Boolean projectsId13) {
        this.projectsId13 = projectsId13;
    }

    @JsonProperty("projects__id-14")
    public Boolean getProjectsId14() {
        return projectsId14;
    }

    @JsonProperty("projects__id-14")
    public void setProjectsId14(Boolean projectsId14) {
        this.projectsId14 = projectsId14;
    }

    @JsonProperty("projects__id-15")
    public Boolean getProjectsId15() {
        return projectsId15;
    }

    @JsonProperty("projects__id-15")
    public void setProjectsId15(Boolean projectsId15) {
        this.projectsId15 = projectsId15;
    }

    @JsonProperty("projects__id-16")
    public Boolean getProjectsId16() {
        return projectsId16;
    }

    @JsonProperty("projects__id-16")
    public void setProjectsId16(Boolean projectsId16) {
        this.projectsId16 = projectsId16;
    }

    @JsonProperty("projects__id-17")
    public Boolean getProjectsId17() {
        return projectsId17;
    }

    @JsonProperty("projects__id-17")
    public void setProjectsId17(Boolean projectsId17) {
        this.projectsId17 = projectsId17;
    }

    @JsonProperty("projects__id-18")
    public Boolean getProjectsId18() {
        return projectsId18;
    }

    @JsonProperty("projects__id-18")
    public void setProjectsId18(Boolean projectsId18) {
        this.projectsId18 = projectsId18;
    }

    @JsonProperty("projects__id-19")
    public Boolean getProjectsId19() {
        return projectsId19;
    }

    @JsonProperty("projects__id-19")
    public void setProjectsId19(Boolean projectsId19) {
        this.projectsId19 = projectsId19;
    }

    @JsonProperty("projects__id-20")
    public Boolean getProjectsId20() {
        return projectsId20;
    }

    @JsonProperty("projects__id-20")
    public void setProjectsId20(Boolean projectsId20) {
        this.projectsId20 = projectsId20;
    }

    @JsonProperty("projects__id-21")
    public Boolean getProjectsId21() {
        return projectsId21;
    }

    @JsonProperty("projects__id-21")
    public void setProjectsId21(Boolean projectsId21) {
        this.projectsId21 = projectsId21;
    }

    @JsonProperty("conditions__allergies-0")
    public String getConditionsAllergies0() {
        return conditionsAllergies0;
    }

    @JsonProperty("conditions__allergies-0")
    public void setConditionsAllergies0(String conditionsAllergies0) {
        this.conditionsAllergies0 = conditionsAllergies0;
    }

    @JsonProperty("conditions__allergies-1")
    public String getConditionsAllergies1() {
        return conditionsAllergies1;
    }

    @JsonProperty("conditions__allergies-1")
    public void setConditionsAllergies1(String conditionsAllergies1) {
        this.conditionsAllergies1 = conditionsAllergies1;
    }

    @JsonProperty("conditions__allergies-2")
    public String getConditionsAllergies2() {
        return conditionsAllergies2;
    }

    @JsonProperty("conditions__allergies-2")
    public void setConditionsAllergies2(String conditionsAllergies2) {
        this.conditionsAllergies2 = conditionsAllergies2;
    }

    @JsonProperty("conditions__allergies-3")
    public String getConditionsAllergies3() {
        return conditionsAllergies3;
    }

    @JsonProperty("conditions__allergies-3")
    public void setConditionsAllergies3(String conditionsAllergies3) {
        this.conditionsAllergies3 = conditionsAllergies3;
    }

    @JsonProperty("conditions__allergies-4")
    public String getConditionsAllergies4() {
        return conditionsAllergies4;
    }

    @JsonProperty("conditions__allergies-4")
    public void setConditionsAllergies4(String conditionsAllergies4) {
        this.conditionsAllergies4 = conditionsAllergies4;
    }

    @JsonProperty("conditions__diseases-0")
    public String getConditionsDiseases0() {
        return conditionsDiseases0;
    }

    @JsonProperty("conditions__diseases-0")
    public void setConditionsDiseases0(String conditionsDiseases0) {
        this.conditionsDiseases0 = conditionsDiseases0;
    }

    @JsonProperty("conditions__diseases-1")
    public String getConditionsDiseases1() {
        return conditionsDiseases1;
    }

    @JsonProperty("conditions__diseases-1")
    public void setConditionsDiseases1(String conditionsDiseases1) {
        this.conditionsDiseases1 = conditionsDiseases1;
    }

    @JsonProperty("conditions__diseases-2")
    public String getConditionsDiseases2() {
        return conditionsDiseases2;
    }

    @JsonProperty("conditions__diseases-2")
    public void setConditionsDiseases2(String conditionsDiseases2) {
        this.conditionsDiseases2 = conditionsDiseases2;
    }

    @JsonProperty("conditions__diseases-3")
    public String getConditionsDiseases3() {
        return conditionsDiseases3;
    }

    @JsonProperty("conditions__diseases-3")
    public void setConditionsDiseases3(String conditionsDiseases3) {
        this.conditionsDiseases3 = conditionsDiseases3;
    }

    @JsonProperty("conditions__diseases-4")
    public String getConditionsDiseases4() {
        return conditionsDiseases4;
    }

    @JsonProperty("conditions__diseases-4")
    public void setConditionsDiseases4(String conditionsDiseases4) {
        this.conditionsDiseases4 = conditionsDiseases4;
    }

    @JsonProperty("conditions__heat-sensitivity")
    public Boolean getConditionsHeatSensitivity() {
        return conditionsHeatSensitivity;
    }

    @JsonProperty("conditions__heat-sensitivity")
    public void setConditionsHeatSensitivity(Boolean conditionsHeatSensitivity) {
        this.conditionsHeatSensitivity = conditionsHeatSensitivity;
    }

    @JsonProperty("conditions__medication-0")
    public String getConditionsMedication0() {
        return conditionsMedication0;
    }

    @JsonProperty("conditions__medication-0")
    public void setConditionsMedication0(String conditionsMedication0) {
        this.conditionsMedication0 = conditionsMedication0;
    }

    @JsonProperty("conditions__vegetarian")
    public Boolean getConditionsVegetarian() {
        return conditionsVegetarian;
    }

    @JsonProperty("conditions__vegetarian")
    public void setConditionsVegetarian(Boolean conditionsVegetarian) {
        this.conditionsVegetarian = conditionsVegetarian;
    }

    @JsonProperty("conditions__lactose-intolerance")
    public Boolean getConditionsLactoseIntolerance() {
        return conditionsLactoseIntolerance;
    }

    @JsonProperty("conditions__lactose-intolerance")
    public void setConditionsLactoseIntolerance(Boolean conditionsLactoseIntolerance) {
        this.conditionsLactoseIntolerance = conditionsLactoseIntolerance;
    }

    @JsonProperty("conditions__egg-intolerance")
    public Boolean getConditionsEggIntolerance() {
        return conditionsEggIntolerance;
    }

    @JsonProperty("conditions__egg-intolerance")
    public void setConditionsEggIntolerance(Boolean conditionsEggIntolerance) {
        this.conditionsEggIntolerance = conditionsEggIntolerance;
    }

    @JsonProperty("conditions__nutrition-0")
    public String getConditionsNutrition0() {
        return conditionsNutrition0;
    }

    @JsonProperty("conditions__nutrition-0")
    public void setConditionsNutrition0(String conditionsNutrition0) {
        this.conditionsNutrition0 = conditionsNutrition0;
    }

    @JsonProperty("conditions__child-treatment-allowed")
    public String getConditionsChildTreatmentAllowed() {
        return conditionsChildTreatmentAllowed;
    }

    @JsonProperty("conditions__child-treatment-allowed")
    public void setConditionsChildTreatmentAllowed(String conditionsChildTreatmentAllowed) {
        this.conditionsChildTreatmentAllowed = conditionsChildTreatmentAllowed;
    }

    @JsonProperty("conditions__health-insurance")
    public String getConditionsHealthInsurance() {
        return conditionsHealthInsurance;
    }

    @JsonProperty("conditions__health-insurance")
    public void setConditionsHealthInsurance(String conditionsHealthInsurance) {
        this.conditionsHealthInsurance = conditionsHealthInsurance;
    }

    @JsonProperty("conditions__emergency-name")
    public String getConditionsEmergencyName() {
        return conditionsEmergencyName;
    }

    @JsonProperty("conditions__emergency-name")
    public void setConditionsEmergencyName(String conditionsEmergencyName) {
        this.conditionsEmergencyName = conditionsEmergencyName;
    }

    @JsonProperty("conditions__emergency-address")
    public String getConditionsEmergencyAddress() {
        return conditionsEmergencyAddress;
    }

    @JsonProperty("conditions__emergency-address")
    public void setConditionsEmergencyAddress(String conditionsEmergencyAddress) {
        this.conditionsEmergencyAddress = conditionsEmergencyAddress;
    }

    @JsonProperty("conditions__emergency-phone-number")
    public String getConditionsEmergencyPhoneNumber() {
        return conditionsEmergencyPhoneNumber;
    }

    @JsonProperty("conditions__emergency-phone-number")
    public void setConditionsEmergencyPhoneNumber(String conditionsEmergencyPhoneNumber) {
        this.conditionsEmergencyPhoneNumber = conditionsEmergencyPhoneNumber;
    }

    @JsonProperty("conditions__family-doctor-name")
    public String getConditionsFamilyDoctorName() {
        return conditionsFamilyDoctorName;
    }

    @JsonProperty("conditions__family-doctor-name")
    public void setConditionsFamilyDoctorName(String conditionsFamilyDoctorName) {
        this.conditionsFamilyDoctorName = conditionsFamilyDoctorName;
    }

    @JsonProperty("conditions__family-doctor-address")
    public String getConditionsFamilyDoctorAddress() {
        return conditionsFamilyDoctorAddress;
    }

    @JsonProperty("conditions__family-doctor-address")
    public void setConditionsFamilyDoctorAddress(String conditionsFamilyDoctorAddress) {
        this.conditionsFamilyDoctorAddress = conditionsFamilyDoctorAddress;
    }

    @JsonProperty("conditions__family-doctor-phone-number")
    public String getConditionsFamilyDoctorPhoneNumber() {
        return conditionsFamilyDoctorPhoneNumber;
    }

    @JsonProperty("conditions__family-doctor-phone-number")
    public void setConditionsFamilyDoctorPhoneNumber(String conditionsFamilyDoctorPhoneNumber) {
        this.conditionsFamilyDoctorPhoneNumber = conditionsFamilyDoctorPhoneNumber;
    }

    @JsonProperty("disabilities__disability-existent")
    public Boolean getDisabilitiesDisabilityExistent() {
        return disabilitiesDisabilityExistent;
    }

    @JsonProperty("disabilities__disability-existent")
    public void setDisabilitiesDisabilityExistent(Boolean disabilitiesDisabilityExistent) {
        this.disabilitiesDisabilityExistent = disabilitiesDisabilityExistent;
    }

    @JsonProperty("disabilities__mark-ag")
    public Boolean getDisabilitiesMarkAg() {
        return disabilitiesMarkAg;
    }

    @JsonProperty("disabilities__mark-ag")
    public void setDisabilitiesMarkAg(Boolean disabilitiesMarkAg) {
        this.disabilitiesMarkAg = disabilitiesMarkAg;
    }

    @JsonProperty("disabilities__mark-h")
    public Boolean getDisabilitiesMarkH() {
        return disabilitiesMarkH;
    }

    @JsonProperty("disabilities__mark-h")
    public void setDisabilitiesMarkH(Boolean disabilitiesMarkH) {
        this.disabilitiesMarkH = disabilitiesMarkH;
    }

    @JsonProperty("disabilities__mark-bl")
    public Boolean getDisabilitiesMarkBl() {
        return disabilitiesMarkBl;
    }

    @JsonProperty("disabilities__mark-bl")
    public void setDisabilitiesMarkBl(Boolean disabilitiesMarkBl) {
        this.disabilitiesMarkBl = disabilitiesMarkBl;
    }

    @JsonProperty("disabilities__mark-gl")
    public Boolean getDisabilitiesMarkGl() {
        return disabilitiesMarkGl;
    }

    @JsonProperty("disabilities__mark-gl")
    public void setDisabilitiesMarkGl(Boolean disabilitiesMarkGl) {
        this.disabilitiesMarkGl = disabilitiesMarkGl;
    }

    @JsonProperty("disabilities__mark-b")
    public Boolean getDisabilitiesMarkB() {
        return disabilitiesMarkB;
    }

    @JsonProperty("disabilities__mark-b")
    public void setDisabilitiesMarkB(Boolean disabilitiesMarkB) {
        this.disabilitiesMarkB = disabilitiesMarkB;
    }

    @JsonProperty("disabilities__mark-g")
    public Boolean getDisabilitiesMarkG() {
        return disabilitiesMarkG;
    }

    @JsonProperty("disabilities__mark-g")
    public void setDisabilitiesMarkG(Boolean disabilitiesMarkG) {
        this.disabilitiesMarkG = disabilitiesMarkG;
    }

    @JsonProperty("disabilities__mark-tbl")
    public Boolean getDisabilitiesMarkTbl() {
        return disabilitiesMarkTbl;
    }

    @JsonProperty("disabilities__mark-tbl")
    public void setDisabilitiesMarkTbl(Boolean disabilitiesMarkTbl) {
        this.disabilitiesMarkTbl = disabilitiesMarkTbl;
    }

    @JsonProperty("disabilities__wheelchair")
    public Boolean getDisabilitiesWheelchair() {
        return disabilitiesWheelchair;
    }

    @JsonProperty("disabilities__wheelchair")
    public void setDisabilitiesWheelchair(Boolean disabilitiesWheelchair) {
        this.disabilitiesWheelchair = disabilitiesWheelchair;
    }

    @JsonProperty("disabilities__additional-tools")
    public String getDisabilitiesAdditionalTools() {
        return disabilitiesAdditionalTools;
    }

    @JsonProperty("disabilities__additional-tools")
    public void setDisabilitiesAdditionalTools(String disabilitiesAdditionalTools) {
        this.disabilitiesAdditionalTools = disabilitiesAdditionalTools;
    }

    @JsonProperty("disabilities__token-available")
    public Boolean getDisabilitiesTokenAvailable() {
        return disabilitiesTokenAvailable;
    }

    @JsonProperty("disabilities__token-available")
    public void setDisabilitiesTokenAvailable(Boolean disabilitiesTokenAvailable) {
        this.disabilitiesTokenAvailable = disabilitiesTokenAvailable;
    }

    @JsonProperty("disabilities__companion-required")
    public Boolean getDisabilitiesCompanionRequired() {
        return disabilitiesCompanionRequired;
    }

    @JsonProperty("disabilities__companion-required")
    public void setDisabilitiesCompanionRequired(Boolean disabilitiesCompanionRequired) {
        this.disabilitiesCompanionRequired = disabilitiesCompanionRequired;
    }

    @JsonProperty("disabilities__companion-for-nursing")
    public Boolean getDisabilitiesCompanionForNursing() {
        return disabilitiesCompanionForNursing;
    }

    @JsonProperty("disabilities__companion-for-nursing")
    public void setDisabilitiesCompanionForNursing(Boolean disabilitiesCompanionForNursing) {
        this.disabilitiesCompanionForNursing = disabilitiesCompanionForNursing;
    }

    @JsonProperty("disabilities__companion-for-health-care")
    public Boolean getDisabilitiesCompanionForHealthCare() {
        return disabilitiesCompanionForHealthCare;
    }

    @JsonProperty("disabilities__companion-for-health-care")
    public void setDisabilitiesCompanionForHealthCare(Boolean disabilitiesCompanionForHealthCare) {
        this.disabilitiesCompanionForHealthCare = disabilitiesCompanionForHealthCare;
    }

    @JsonProperty("disabilities__companion-for-mobility")
    public Boolean getDisabilitiesCompanionForMobility() {
        return disabilitiesCompanionForMobility;
    }

    @JsonProperty("disabilities__companion-for-mobility")
    public void setDisabilitiesCompanionForMobility(Boolean disabilitiesCompanionForMobility) {
        this.disabilitiesCompanionForMobility = disabilitiesCompanionForMobility;
    }

    @JsonProperty("disabilities__companion-for-orientation")
    public Boolean getDisabilitiesCompanionForOrientation() {
        return disabilitiesCompanionForOrientation;
    }

    @JsonProperty("disabilities__companion-for-orientation")
    public void setDisabilitiesCompanionForOrientation(Boolean disabilitiesCompanionForOrientation) {
        this.disabilitiesCompanionForOrientation = disabilitiesCompanionForOrientation;
    }

    @JsonProperty("disabilities__companion-social")
    public Boolean getDisabilitiesCompanionSocial() {
        return disabilitiesCompanionSocial;
    }

    @JsonProperty("disabilities__companion-social")
    public void setDisabilitiesCompanionSocial(Boolean disabilitiesCompanionSocial) {
        this.disabilitiesCompanionSocial = disabilitiesCompanionSocial;
    }

    @JsonProperty("disabilities__affected-senses-0")
    public String getDisabilitiesAffectedSenses0() {
        return disabilitiesAffectedSenses0;
    }

    @JsonProperty("disabilities__affected-senses-0")
    public void setDisabilitiesAffectedSenses0(String disabilitiesAffectedSenses0) {
        this.disabilitiesAffectedSenses0 = disabilitiesAffectedSenses0;
    }

    @JsonProperty("disabilities__companion-additional-notes")
    public String getDisabilitiesCompanionAdditionalNotes() {
        return disabilitiesCompanionAdditionalNotes;
    }

    @JsonProperty("disabilities__companion-additional-notes")
    public void setDisabilitiesCompanionAdditionalNotes(String disabilitiesCompanionAdditionalNotes) {
        this.disabilitiesCompanionAdditionalNotes = disabilitiesCompanionAdditionalNotes;
    }

    @JsonProperty("disabilities__companion-help-finding-required")
    public Boolean getDisabilitiesCompanionHelpFindingRequired() {
        return disabilitiesCompanionHelpFindingRequired;
    }

    @JsonProperty("disabilities__companion-help-finding-required")
    public void setDisabilitiesCompanionHelpFindingRequired(Boolean disabilitiesCompanionHelpFindingRequired) {
        this.disabilitiesCompanionHelpFindingRequired = disabilitiesCompanionHelpFindingRequired;
    }

    @JsonProperty("disabilities__companion-usual-service")
    public String getDisabilitiesCompanionUsualService() {
        return disabilitiesCompanionUsualService;
    }

    @JsonProperty("disabilities__companion-usual-service")
    public void setDisabilitiesCompanionUsualService(String disabilitiesCompanionUsualService) {
        this.disabilitiesCompanionUsualService = disabilitiesCompanionUsualService;
    }

    @JsonProperty("disabilities__companion-cost-takeover")
    public Boolean getDisabilitiesCompanionCostTakeover() {
        return disabilitiesCompanionCostTakeover;
    }

    @JsonProperty("disabilities__companion-cost-takeover")
    public void setDisabilitiesCompanionCostTakeover(Boolean disabilitiesCompanionCostTakeover) {
        this.disabilitiesCompanionCostTakeover = disabilitiesCompanionCostTakeover;
    }

    @JsonProperty("declaration__going-home-alone-allowed")
    public String getDeclarationGoingHomeAloneAllowed() {
        return declarationGoingHomeAloneAllowed;
    }

    @JsonProperty("declaration__going-home-alone-allowed")
    public void setDeclarationGoingHomeAloneAllowed(String declarationGoingHomeAloneAllowed) {
        this.declarationGoingHomeAloneAllowed = declarationGoingHomeAloneAllowed;
    }

    @JsonProperty("declaration__horse-riding-allowed")
    public String getDeclarationHorseRidingAllowed() {
        return declarationHorseRidingAllowed;
    }

    @JsonProperty("declaration__horse-riding-allowed")
    public void setDeclarationHorseRidingAllowed(String declarationHorseRidingAllowed) {
        this.declarationHorseRidingAllowed = declarationHorseRidingAllowed;
    }

    @JsonProperty("declaration__swimming-allowed")
    public String getDeclarationSwimmingAllowed() {
        return declarationSwimmingAllowed;
    }

    @JsonProperty("declaration__swimming-allowed")
    public void setDeclarationSwimmingAllowed(String declarationSwimmingAllowed) {
        this.declarationSwimmingAllowed = declarationSwimmingAllowed;
    }

    @JsonProperty("declaration__swimming-badge")
    public String getDeclarationSwimmingBadge() {
        return declarationSwimmingBadge;
    }

    @JsonProperty("declaration__swimming-badge")
    public void setDeclarationSwimmingBadge(String declarationSwimmingBadge) {
        this.declarationSwimmingBadge = declarationSwimmingBadge;
    }

    @JsonProperty("privacy-policy__confirmation")
    public Boolean getPrivacyPolicyConfirmation() {
        return privacyPolicyConfirmation;
    }

    @JsonProperty("privacy-policy__confirmation")
    public void setPrivacyPolicyConfirmation(Boolean privacyPolicyConfirmation) {
        this.privacyPolicyConfirmation = privacyPolicyConfirmation;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
