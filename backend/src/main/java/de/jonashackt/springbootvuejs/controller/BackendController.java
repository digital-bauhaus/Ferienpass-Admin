package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.domain.*;
import de.jonashackt.springbootvuejs.repository.*;
import org.hibernate.annotations.Check;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;



@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

    //INITIAL REQUEST FOR FORM OBJECT
    @RequestMapping(path = "/form")
    public @ResponseBody
    Form sendinitialForm() {

        //!!!
        //Return Type of function is <String> only for debugging, final return type is a Form object

        //Section List of Form object
        List<Section> sections= new ArrayList<>();

        //Building structure of form-data.json
        Form form = new Form("Ferienpass Weimar – Anmeldung",sections);

        //Form Object holds 7 sections
        //SECTION 1 GRUNDDATEN
        //All Params of every TextField
        Param textfield1 = new Param("Familienname",true);
        Param textfield2 = new Param("Vorname meines Kindes",true);
        Param textfield3 = new Param("Geburtsdatum meines Kindes",true);
        Param textfield4 = new Param("Straße",true);
        Param textfield5 = new Param("Wohnort",true);
        Param textfield6 = new Param("Telefon",true,"tel");

        //Param Lists of every Components(TextFields) in Grunddaten
        List<Param> params_component_param1 = new ArrayList<>();
        List<Param> params_component_param2 = new ArrayList<>();
        List<Param> params_component_param3 = new ArrayList<>();
        List<Param> params_component_param4 = new ArrayList<>();
        List<Param> params_component_param5 = new ArrayList<>();
        List<Param> params_component_param6 = new ArrayList<>();

        //adding all parameter to List of components of Grunddaten
        params_component_param1.add(textfield1);
        params_component_param2.add(textfield2);
        params_component_param3.add(textfield3);
        params_component_param4.add(textfield4);
        params_component_param5.add(textfield5);
        params_component_param6.add(textfield6);

        //creating all Components in Grunddaten
        Component component_param1 = new Component("TextField", params_component_param1);
        Component component_param2 = new Component("TextField", params_component_param2);
        Component component_param3 = new Component("TextField", params_component_param3);
        Component component_param4 = new Component("TextField", params_component_param4);
        Component component_param5 = new Component("TextField", params_component_param5);
        Component component_param6 = new Component("TextField", params_component_param6);

        List<Component> components_param1 = new ArrayList<>();
    //adding all Components to Grunddaten Component List
        components_param1.add(component_param1);
        components_param1.add(component_param2);
        components_param1.add(component_param3);
        components_param1.add(component_param4);
        components_param1.add(component_param5);
        components_param1.add(component_param6);


        Param param1 = new Param("Grunddaten",true, components_param1);
        List<Param> params_s1 = new ArrayList<>();
        params_s1.add(param1);
        Component c_s1 = new Component("Group", params_s1);
        List<Component> components_sec1 = new ArrayList<>();
        components_sec1.add(c_s1);
        //Section 1
        Section s1_grunddaten = new Section("Grunddaten",components_sec1);
        sections.add(s1_grunddaten);
        //Section 2

        List<Component> components_param2 = new ArrayList<>();
        //Checkboxen fuer jede Veranstaltung
        List<Project> allprojects = projectRepository.findAllProjects();
        for (Project element : allprojects){
            List<Param> temp_params_cb1 = new ArrayList<>();
            Param temp = new Param(element.getName(),element.getDate(),"org",element.getSlotsFree(),element.getSlots());
            temp_params_cb1.add(temp);
            Component temp_compo = new Component("Checkbox",temp_params_cb1);
            components_param2.add(temp_compo);
        }



        Param param2 = new Param("Angebote",true, "Mein Kind möchte an folgenden Veranstaltungen teilnehmen:","Hinweis: Die Bestätigung des Platzes erfolgt bei der Anmeldung entsprechend der zur Verfügung stehenden Kapazitäten für die Angebote. Sollte ein Angebot seitens der Veranstalter aus unvorhergesehenen Gründen abgesagt werden, besteht kein Anspruch auf ein Ersatzangebot. Der gezahlte Beitrag für dieses Angebot wird Ihnen komplett zurück erstattet.", components_param2);
        List<Param> params_s2 = new ArrayList<>();
        params_s2.add(param2);
        Component c_s2 = new Component("Group", params_s2);
        List<Component> components_sec2 = new ArrayList<>();
        components_sec2.add(c_s2);
        Section s2_angebote = new Section("Angebote",components_sec2);
        sections.add(s2_angebote);


        //SECTION 3 weggelassen, weil sie nicht benötigt wird für die Anmeldung

        //SECTION 4
        Param textfield4_1_param1 = new Param("Neuen Eintrag hinzufügen","z. B. Heuschnupfen",true);
        Param textfield4_2_param1 = new Param("Neuen Eintrag hinzufügen","z. B. Epilepsie",true);
        Param textfield4_3_param1 = new Param("Neuen Eintrag hinzufügen","z. B. Diazepam",true);
        Param ernaehrung_compo1_params1_param1 = new Param("Vegetarier");
        Param ernaehrung_compo1_params1_param2 = new Param("Laktose-Unverträglichkeit");
        Param ernaehrung_compo1_params1_param3 = new Param("Eier-Unverträglichkeit");
        Param textfield_dyn_list4_param1 = new Param("Neuen Eintrag hinzufügen","z. B. Milchpulver-Unverträglichkeit",true);
        Param radiobutton1_param1 = new Param("Ja");
        Param radiobutton2_param1 = new Param("Nein");
        Param kraka_compo1_param1 = new Param("Name", true);
        Param notfall_compo1_param1 = new Param("Name",true);
        Param notfall_compo2_param1 = new Param("Anschrift",true);
        Param notfall_compo3_param1 = new Param("Telefon",true,"tel");
        Param arzt_compo1_param1 = new Param("Name", true);
        Param arzt_compo2_param1 = new Param("Anschrift", true);
        Param arzt_compo3_param1 = new Param("Telefon", true,"tel");

        List<Param> textfield4_1_params = new ArrayList<>();
        List<Param> textfield4_2_params = new ArrayList<>();
        List<Param> textfield4_3_params = new ArrayList<>();
        List<Param> textfield_dyn_list4_params = new ArrayList<>();

        List<Param> ernaehrung_compo1_params1 = new ArrayList<>();
        List<Param> ernaehrung_compo1_params2 = new ArrayList<>();
        List<Param> ernaehrung_compo1_params3 = new ArrayList<>();
        List<Param> kraka_compo1_params = new ArrayList<>();
        List<Param> notfall_compo1_params = new ArrayList<>();
        List<Param> notfall_compo2_params = new ArrayList<>();
        List<Param> notfall_compo3_params = new ArrayList<>();
        List<Param> arzt_compo1_params = new ArrayList<>();
        List<Param> arzt_compo2_params = new ArrayList<>();
        List<Param> arzt_compo3_params = new ArrayList<>();
        List<Param> radiobutton1_params = new ArrayList<>();
        List<Param> radiobutton2_params = new ArrayList<>();

        arzt_compo1_params.add(arzt_compo1_param1);
        arzt_compo2_params.add(arzt_compo2_param1);
        arzt_compo3_params.add(arzt_compo3_param1);

        notfall_compo1_params.add(notfall_compo1_param1);
        notfall_compo2_params.add(notfall_compo2_param1);
        notfall_compo3_params.add(notfall_compo3_param1);

        kraka_compo1_params.add(kraka_compo1_param1);
        ernaehrung_compo1_params1.add(ernaehrung_compo1_params1_param1);
        ernaehrung_compo1_params2.add(ernaehrung_compo1_params1_param2);
        ernaehrung_compo1_params3.add(ernaehrung_compo1_params1_param3);

        radiobutton1_params.add(radiobutton1_param1);
        radiobutton2_params.add(radiobutton2_param1);

        textfield4_1_params.add(textfield4_1_param1);
        textfield4_2_params.add(textfield4_2_param1);
        textfield4_3_params.add(textfield4_3_param1);
        textfield_dyn_list4_params.add(textfield_dyn_list4_param1);

        Textfield textfield4_1 = new Textfield("TextField",textfield4_1_params);
        Textfield textfield4_2 = new Textfield("TextField", textfield4_2_params);
        Textfield textfield4_3 = new Textfield("TextField", textfield4_3_params);
        Textfield textfield_dyn_list4 = new Textfield("TextField", textfield_dyn_list4_params);

        Param hitzeempfi_compo1_param1 = new Param("Mein Kind ist hitzeempfindlich.");
        List<Param> hitzeempfi_compo1_params = new ArrayList<>();
        hitzeempfi_compo1_params.add(hitzeempfi_compo1_param1);
        Component hitzeempfi_compo1 = new Component("Checkbox", hitzeempfi_compo1_params);
        List<Component> hitzeempfi_compos = new ArrayList<>();
        hitzeempfi_compos.add(hitzeempfi_compo1);
        Component ernaehrung_compo1 = new Component("Checkbox", ernaehrung_compo1_params1);
        Component ernaehrung_compo2 = new Component("Checkbox", ernaehrung_compo1_params2);
        Component ernaehrung_compo3 = new Component("Checkbox", ernaehrung_compo1_params3);
        Component radiobutton1 = new Component("RadioButton",radiobutton1_params);
        Component radiobutton2 = new Component("RadioButton",radiobutton2_params);
        Component kraka_compo1 = new Component("TextField", kraka_compo1_params);
        Component notfall_compo1 = new Component("TextField", notfall_compo1_params);
        Component notfall_compo2 = new Component("TextField", notfall_compo2_params);
        Component notfall_compo3 = new Component("TextField", notfall_compo3_params);
        Component arzt_compo1 = new Component("TextField", arzt_compo1_params);
        Component arzt_compo2 = new Component("TextField", arzt_compo2_params);
        Component arzt_compo3 = new Component("TextField", arzt_compo3_params);


        List<Component> ernaehrung_compos = new ArrayList<>();
        List<Component> behandlung_compos = new ArrayList<>();
        List<Component> kraka_compos = new ArrayList<>();
        List<Component> notfall_compos = new ArrayList<>();
        List<Component> arzt_compos = new ArrayList<>();

        ernaehrung_compos.add(ernaehrung_compo1);
        ernaehrung_compos.add(ernaehrung_compo2);
        ernaehrung_compos.add(ernaehrung_compo3);
        behandlung_compos.add(radiobutton1);
        behandlung_compos.add(radiobutton2);
        kraka_compos.add(kraka_compo1);
        notfall_compos.add(notfall_compo1);
        notfall_compos.add(notfall_compo2);
        notfall_compos.add(notfall_compo3);
        arzt_compos.add(arzt_compo1);
        arzt_compos.add(arzt_compo2);
        arzt_compos.add(arzt_compo3);



        Param allergien = new Param("Allergien","Bei meinem Kind muss auf folgende Allergie(n) geachtet werden.",textfield4_1);
        Param krankheiten = new Param("Krankheiten","Krankheiten des Kindes bitte hier angeben.",textfield4_2);
        Param hitzeempfi = new Param("Hitzeempfindlichkeit", hitzeempfi_compos);
        Param medikamente = new Param("Medikamente","Vom Kind einzunehmende Medikamente hier eintragen.",textfield4_3);
        Param ernaehrung = new Param("Ernährungsbesonderheiten", ernaehrung_compos);
        Param weitere = new Param("Weitere Ernährungsbesonderheiten","Bitte beschreiben.",textfield_dyn_list4);
        Param behandlung = new Param("Behandlungserlaubnis bei Erkrankungen und Unfällen", behandlung_compos);
        Param krankenkasse = new Param("Krankenkasse", kraka_compos);
        Param notfall_param = new Param("In Notfällen zu informieren", notfall_compos);
        Param arzt_param = new Param("Hausarzt", arzt_compos);

        List<Param> dyn_list1_params = new ArrayList<>();
        dyn_list1_params.add(allergien);
        List<Param> dyn_list2_params = new ArrayList<>();
        dyn_list2_params.add(krankheiten);
        List<Param> group1_params = new ArrayList<>();
        group1_params.add(hitzeempfi);
        List<Param> dyn_list3_params = new ArrayList<>();
        group1_params.add(medikamente);
        List<Param> group2_params = new ArrayList<>();
        group1_params.add(ernaehrung);
        List<Param> dyn_list4_params = new ArrayList<>();
        dyn_list4_params.add(weitere);
        List<Param> radio_params = new ArrayList<>();
        radio_params.add(behandlung);
        List<Param> kraka_params = new ArrayList<>();
        kraka_params.add(krankenkasse);
        List<Param> notfall_params = new ArrayList<>();
        notfall_params.add(notfall_param);
        List<Param> arzt_params = new ArrayList<>();
        arzt_params.add(arzt_param);

        Component dyn_list1 =  new Component("DynamicList",dyn_list1_params);
        Component dyn_list2 = new Component("DynamicList",dyn_list2_params);
        Component group1 = new Component("group", group1_params);
        Component dyn_list3 = new Component("DynamicList", dyn_list3_params);
        Component group2 = new Component("Group", group2_params);
        Component dyn_list4 = new Component("DynamicList",dyn_list4_params);
        Component radiogaga = new Component("RadioGroup", radio_params);
        Component kraka = new Component("Group", kraka_params);
        Component notfall = new Component("Group", notfall_params);
        Component arzt = new Component("Group", arzt_params);
        List<Component> components_sec4 = new ArrayList<>();

        components_sec4.add(dyn_list1);
        components_sec4.add(dyn_list2);
        components_sec4.add(group1);
        components_sec4.add(dyn_list3);
        components_sec4.add(group2);
        components_sec4.add(dyn_list4);
        components_sec4.add(radiogaga);
        components_sec4.add(kraka);
        components_sec4.add(notfall);
        components_sec4.add(arzt);

        Section s4_allerg_krank = new Section("Allergien, Krankheiten, ...",components_sec4);
        sections.add(s4_allerg_krank);

        //SECTION 5#
        Param tf_param1= new Param("Neuen Eintrag hinzufügen","z. B. Sicht",true);
        List<Param> tf_params = new ArrayList<>();
        tf_params.add(tf_param1);
        Textfield textfield101 = new Textfield("TextField", tf_params);
        Param group_1_param1_compo1_param1 = new Param("Pflege");
        Param group_1_param1_compo2_param2 = new Param("Medizinische Versorgung");
        Param group_1_param1_compo3_param3 = new Param("Mobilität");
        Param group_1_param1_compo4_param4 = new Param("Orientierung");
        Param group_1_param1_compo5_param5 = new Param("Soziale Begleitung");
        Param group_1_param1_compo5_param6 = new Param("Sinneswahrnehmung", "Beeinträchtigte Sinneswahrnehmungen angeben", textfield101);
        List<Param> group_1_param1_compo1_params = new ArrayList<>();
        List<Param> group_1_param1_compo2_params = new ArrayList<>();
        List<Param> group_1_param1_compo3_params = new ArrayList<>();
        List<Param> group_1_param1_compo4_params = new ArrayList<>();
        List<Param> group_1_param1_compo5_params = new ArrayList<>();
        List<Param> group_1_param1_compo6_params = new ArrayList<>();

        group_1_param1_compo1_params.add(group_1_param1_compo1_param1);
        group_1_param1_compo2_params.add(group_1_param1_compo2_param2);
        group_1_param1_compo3_params.add(group_1_param1_compo3_param3);
        group_1_param1_compo4_params.add(group_1_param1_compo4_param4);
        group_1_param1_compo5_params.add(group_1_param1_compo5_param5);
        group_1_param1_compo6_params.add(group_1_param1_compo5_param6);

        Component group_1_param1_compo1 = new Component("Checkbox", group_1_param1_compo1_params);
        Component group_1_param1_compo2 = new Component("Checkbox", group_1_param1_compo2_params);
        Component group_1_param1_compo3 = new Component("Checkbox", group_1_param1_compo3_params);
        Component group_1_param1_compo4 = new Component("Checkbox", group_1_param1_compo4_params);
        Component group_1_param1_compo5 = new Component("Checkbox", group_1_param1_compo5_params);
        Component dyn_list_group_1 = new Component("DynamicList", group_1_param1_compo6_params);

        List<Component> group_1_param1_compos = new ArrayList<>();

        group_1_param1_compos.add(group_1_param1_compo1);
        group_1_param1_compos.add(group_1_param1_compo2);
        group_1_param1_compos.add(group_1_param1_compo3);
        group_1_param1_compos.add(group_1_param1_compo4);
        group_1_param1_compos.add(group_1_param1_compo5);
        group_1_param1_compos.add(dyn_list_group_1);

        Param checkbox5_1_param1 = new Param("„aG“ (Außergewöhnliche Gehbehinderung)");
        Param checkbox5_2_param1 = new Param("„H“ (Hilflosigkeit)");
        Param checkbox5_3_param1 = new Param("„Bl“ (Blind)");
        Param checkbox5_4_param1 = new Param("„Gl“ (Gehörlos)");
        Param checkbox5_5_param1 = new Param("„B“ (Berechtigung zur Mitnahme einer Begleitperson)");
        Param checkbox5_6_param1 = new Param("„G“ (Erhebliche Beeinträchtigung der Bewegungsfähigkeit im Straßenverkehr)");
        Param checkbox5_7_param1 = new Param("„TBL“ (Taubblind)");
        Param checkbox5_8_param1 = new Param("Rollstuhlnutzung");
        Param checkbox5_9_param1 = new Param("Weitere Hilfsmittel beschreiben:");
        Param checkbox5_10_param1 = new Param("Wertmarke vorhanden");
        Param checkbox5_11_param1 = new Param("Mein Kind hat im Schulalltag eine Schulbegleitung und benötigt auch während der Ferienfreizeit eine Begleitperson");
        Param group_1_param1 = new Param("Wofür wird die Begleitperson benötigt?", group_1_param1_compos);
        Param textarea00_param1 = new Param("Darauf ist im Umgang mit meinem Kind unbedingt zu achten:");
        Param checkbox11_param1 = new Param("Wir benötigen Unterstützung bei der Organisation der Begleitperson");
        Param textarea2_param1 = new Param("Welcher Dienst ist in der Regel für die Begleitung/Betreuung zuständig? (Bitte Kontaktdaten des Dienstes/der Dienste angeben):");
        Param checkbox22_param1 = new Param("Hiermit beantrage ich die Übernahme der Kosten für die Begleitung/Betreuung o.g. Kindes während der Teilnahme an den Ferienfreizeiten gemäß der Anmeldung auf Seite 1. Auf die Förderung einer Begleitperson für Ferienfreizeiten besteht kein Rechtsanspruch. Die  bedürftigkeitsabhängige Prüfung erfolgt am Einzelfall durch das Amt für Familie und Soziales der Stadt Weimar. Voraussetzung hierfür ist, dass das Kind als Hauptwohnsitz in Weimar lebt.");

        List<Param> checkbox5_1_params = new ArrayList<>();
        List<Param> checkbox5_2_params = new ArrayList<>();
        List<Param> checkbox5_3_params = new ArrayList<>();
        List<Param> checkbox5_4_params = new ArrayList<>();
        List<Param> checkbox5_5_params = new ArrayList<>();
        List<Param> checkbox5_6_params = new ArrayList<>();
        List<Param> checkbox5_7_params = new ArrayList<>();
        List<Param> checkbox5_8_params = new ArrayList<>();
        List<Param> checkbox5_9_params = new ArrayList<>();
        List<Param> checkbox5_10_params = new ArrayList<>();
        List<Param> checkbox5_11_params = new ArrayList<>();
        List<Param> group_1_params = new ArrayList<>();

        List<Param> textarea00_params = new ArrayList<>();
        List<Param> checkbox11_params = new ArrayList<>();
        List<Param> textarea2_params = new ArrayList<>();
        List<Param> checkbox22_params = new ArrayList<>();

        textarea00_params.add(textarea00_param1);
        checkbox11_params.add(checkbox11_param1);
        textarea2_params.add(textarea2_param1);
        checkbox22_params.add(checkbox22_param1);

        checkbox5_8_params.add(checkbox5_8_param1);
        checkbox5_9_params.add(checkbox5_9_param1);
        checkbox5_1_params.add(checkbox5_1_param1);
        checkbox5_2_params.add(checkbox5_2_param1);
        checkbox5_3_params.add(checkbox5_3_param1);
        checkbox5_4_params.add(checkbox5_4_param1);
        checkbox5_5_params.add(checkbox5_5_param1);
        checkbox5_6_params.add(checkbox5_6_param1);
        checkbox5_7_params.add(checkbox5_7_param1);
        checkbox5_10_params.add(checkbox5_10_param1);
        checkbox5_11_params.add(checkbox5_11_param1);
        group_1_params.add(group_1_param1);

        Param checkbox_dep_sec_param1 = new Param("Bei meinem Kind liegt eine Beeinträchtigung vor");
        Component checkbox5_1 = new Component("Checkbox", checkbox5_1_params );
        Component checkbox5_2 = new Component("Checkbox", checkbox5_2_params );
        Component checkbox5_3 = new Component("Checkbox", checkbox5_3_params );
        Component checkbox5_4 = new Component("Checkbox", checkbox5_4_params );
        Component checkbox5_5 = new Component("Checkbox", checkbox5_5_params );
        Component checkbox5_6 = new Component("Checkbox", checkbox5_6_params );
        Component checkbox5_7 = new Component("Checkbox", checkbox5_7_params );
        Component hilfsmittel_compo1 = new Component("Checkbox", checkbox5_8_params);
        Component hilfsmittel_compo2 = new Component("TextArea", checkbox5_9_params);
        Component checkbox5_10 = new Component("Checkbox", checkbox5_10_params);
        Checkbox checkbox5_11 = new Checkbox("Checkbox",checkbox5_11_params);
        Component group_1 = new Component("Group", group_1_params);
        Component textarea00 = new Component("TextArea", textarea00_params);
        Component checkbox11 = new Component("TextArea", checkbox11_params);
        Component textarea2= new Component("TextArea", textarea2_params);
        Component checkbox22 = new Component("TextArea", checkbox22_params);

        List<Param> checkbox_dep_sec_params = new ArrayList<>();
        List<Component> merkzeichen_compos = new ArrayList<>();
        List<Component> hilfsmittel_compos = new ArrayList<>();
        List<Component> wertmarke_compos = new ArrayList<>();
        List<Component> dep_sec_2_compos = new ArrayList<>();
        merkzeichen_compos.add(checkbox5_1);
        merkzeichen_compos.add(checkbox5_2);
        merkzeichen_compos.add(checkbox5_3);
        merkzeichen_compos.add(checkbox5_4);
        merkzeichen_compos.add(checkbox5_5);
        merkzeichen_compos.add(checkbox5_6);
        merkzeichen_compos.add(checkbox5_7);
        hilfsmittel_compos.add(hilfsmittel_compo1);
        hilfsmittel_compos.add(hilfsmittel_compo2);
        wertmarke_compos.add(checkbox5_10);
        dep_sec_2_compos.add(group_1);
        dep_sec_2_compos.add(checkbox11);
        dep_sec_2_compos.add(textarea2);
        dep_sec_2_compos.add(checkbox22);
        dep_sec_2_compos.add(textarea00);


        checkbox_dep_sec_params.add(checkbox_dep_sec_param1);
        Checkbox checkbox_dep_sec = new Checkbox("Checkbox", checkbox_dep_sec_params);
        Param dep_sec_param1 = new Param("Beeinträchtigung", checkbox_dep_sec);
        Param group1_5_param1 = new Param("Merkzeichen", merkzeichen_compos);
        Param group2_5_param1 = new Param("Hilfsmittel", hilfsmittel_compos);
        Param group3_5_param1 = new Param("Wertmarke", wertmarke_compos);
        Param dep_sec_2_param1 = new Param("Begleitung", checkbox5_11 ,dep_sec_2_compos);
        List<Param> dep_sec_params = new ArrayList<>();
        List<Param> group1_5_params = new ArrayList<>();
        List<Param> group2_5_params = new ArrayList<>();
        List<Param> group3_5_params = new ArrayList<>();
        List<Param> dep_sec_2_params = new ArrayList<>();
        dep_sec_params.add(dep_sec_param1);
        group1_5_params.add(group1_5_param1);
        group2_5_params.add(group2_5_param1);
        group3_5_params.add(group3_5_param1);
        dep_sec_2_params.add(dep_sec_2_param1);


        Component dep_sec = new Component("DependingSection", dep_sec_params);
        Component group1_5 = new Component("Group", group1_5_params);
        Component group2_5 = new Component("Group", group2_5_params);
        Component group3_5 = new Component("Group", group3_5_params);
        Component dep_sec_2 = new Component("DependingSection", dep_sec_2_params);
        List<Component> components_sec5 = new ArrayList<>();
        components_sec5.add(dep_sec);
        components_sec5.add(group1_5);
        components_sec5.add(group2_5);
        components_sec5.add(group3_5);

        Section s5_behinderung = new Section("Angaben bei Behinderung", components_sec5);
        sections.add(s5_behinderung);

        //Section 6

        //Section s6_erklaerung = new Section("Erklärung", components_sec6);
        //sections.add(s6_erklaerung);


        LOG.info("GET called on /form resource || returned Form with ID= " + form.getForm_id());
        //return Form Object (-> JSON Structure from which Anmeldung is created)
        return form;
    }

    @RequestMapping(path = "/hello")
    public @ResponseBody
    String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }

    @RequestMapping(path = "/allusers")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<User> showAllUsers() {
        LOG.info("GET called on /allusers resource");
        return userRepository.findAllUsers();
    }

    @RequestMapping(path = "/allprojects")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<Project> showAllProjects() {
        LOG.info("GET called on /allprojects resource");
        return projectRepository.findAllProjects();
    }

    // SHOW ALL PROJECTS OF ONE USER
    @RequestMapping(path = "/projectsof")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<Project> showProjectsOfUser(@RequestParam String firstName, @RequestParam String lastName) {
        LOG.info("GET called on /projectsof resource");
        return userRepository.findProjectsByFirstNameAndLastName(firstName, lastName);
    }

    // CREATE A SAMPLE PROJECT
    @RequestMapping(path = "/sampleproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewProject() {
        // EXAMPLE Project
        Project project = new Project("Ball Werfen", "10.05.2018", 10, 20, 2, 1, "www.google.com", new ArrayList<>());
        projectRepository.save(project);

        LOG.info(project.toString() + " successfully saved into DB");

        return project.getProject_id();
    }

    // CREATE A SAMPLE USER
    @RequestMapping(path = "/sampleuser")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewUser() {
        // EXAMPLE USER
        Date registerDate = new Date();
        String registerDateString = format.format(registerDate);
        Doctor doctor = new Doctor("Eich", "Route 1 Alabastia, 39829",
                "555-6891");
        Contact contact = new Contact("Igor Eich", "Route 4 Neuborkia  96825", "555-2532");
        Project project1 = new Project("Ball werfen", registerDateString, 10, 20, 3, 1,"www.google.com", new ArrayList<>());
        FoodLimit limit1 = new FoodLimit("Laktoseintoleranz", "");
        Illness limit2 = new Illness("Was ganz dolle schlimmes", "Macht immer richtig komische Sachen", "Honigmelone");
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projectRepository.save(project1);
        List<Limitation> limits = new ArrayList<>();
        limits.add(limit1);
        limits.add(limit2);
        User user = new User("Gary", "Eich", "10.01.1999", registerDateString, "Route 1",
                "Neuborkia",
                "96826", "555-5262", "437647298", false, contact,
                true, true, true, true, doctor,
                projects, limits, null);
        userRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }




    // ADD NEW USER
    @RequestMapping(path = "/adduser", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewUser(@RequestBody User user) {

        userRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }

    // UPDATE USER INFORMATION
    @RequestMapping(path = "/updateuser")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long updateUser(@RequestParam long id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String birthDate,
                    @RequestParam String street, @RequestParam String city, @RequestParam String postcode,
                    @RequestParam String telephone, @RequestParam String healthcareNr, @RequestParam boolean allowTreatment,
                    @RequestParam String contactName, @RequestParam String contactAddress, @RequestParam String contactTelephone,
                    @RequestParam boolean allowHomeAlone, @RequestParam boolean allowRiding,
                    @RequestParam boolean allowSwimming, @RequestParam boolean hasPayed, @RequestParam String doctorName,
                    @RequestParam String doctorAddress, @RequestParam String doctorTelephone) {

        User updatedUser = userRepository.findOne(id);
        updatedUser.setFirstName(firstName);
        updatedUser.setLastName((lastName));
        updatedUser.setBirthDate(birthDate);
        updatedUser.setPostcode(postcode);
        updatedUser.setStreet(street);
        updatedUser.setCity(city);
        updatedUser.setTelephone(telephone);
        updatedUser.setHealthcareNr(healthcareNr);
        updatedUser.setAllowTreatment(allowTreatment);
        updatedUser.getEmergencyContact().setName(contactName);
        updatedUser.getEmergencyContact().setAddress(contactAddress);
        updatedUser.getEmergencyContact().setTelephone(contactTelephone);
        updatedUser.setAllowHomeAlone(allowHomeAlone);
        updatedUser.setAllowRiding(allowRiding);
        updatedUser.setAllowSwimming(allowSwimming);
        updatedUser.setHasPayed(hasPayed);
        updatedUser.getDoctor().setName(doctorName);
        updatedUser.getDoctor().setAddress(doctorAddress);
        updatedUser.getDoctor().setTelephone(doctorTelephone);
        userRepository.save(updatedUser);

        LOG.info(updatedUser.toString() + " successfully saved into DB");

        return updatedUser.getId();
    }

    // GET USER INFORMATION BY ID
    @GetMapping(path = "/user/{id}")
    public @ResponseBody
    User getUserById(@PathVariable("id") long id) {
        return userRepository.findOne(id);
    }

    // GET PROJECT INFORMATION BY ID
    @GetMapping(path = "/project/{project_id}")
    public @ResponseBody
    Project getProjectById(@PathVariable("project_id") Long project_id) {
        return projectRepository.findOne(project_id);
    }

    // CREATE NEW PROJECT
    @RequestMapping(path = "/createproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long addNewProject(@RequestParam String name, @RequestParam String date, @RequestParam int age, @RequestParam int price, @RequestParam int slots,
                       @RequestParam int slotsReserved, @RequestParam String weblink) {
        Project project = new Project(name, date, age, price, slots, slotsReserved, weblink, new ArrayList<>());
        projectRepository.save(project);
        LOG.info(project.toString() + "successfully saved into DB");

        return project.getProject_id();
    }

    // UPDATE PROJECT INFORMATION
    @RequestMapping(path = "/updateProject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Long updateProject(@RequestParam long id, @RequestParam String name, @RequestParam String date, @RequestParam int age, @RequestParam int price, @RequestParam int slots,
                       @RequestParam int slotsReserved, @RequestParam String weblink) {
        Project updatedProject = projectRepository.findOne(id);
        updatedProject.setName(name);
        updatedProject.setDate(date);
        updatedProject.setAge(age);
        updatedProject.setPrice(price);
        updatedProject.setSlots(slots);
        updatedProject.setSlotsReserved(slotsReserved);
        updatedProject.setWeblink(weblink);
        projectRepository.save(updatedProject);
        LOG.info(updatedProject.toString() + "successfully updated/saved into DB");

        return updatedProject.getProject_id();
    }

    // DELETE PROJECT
    @RequestMapping(path = "/deleteproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    String deleteProject(@RequestParam Long project_id) {

        Project theproj = projectRepository.findOne(project_id);
        theproj.setAlive(false);
        projectRepository.save(theproj);
        LOG.info(theproj.toString() + "deleted from DB");

        return theproj.toString();
    }

    // GET ALL PROJECT OF ONE USER
    @RequestMapping(path = "/projectsofuser")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<Project> getProjectsOfUser(@RequestParam long id) {

        User user = userRepository.findOne(id);
        LOG.info("Returned all Projects of: " + user.toString());
        return user.getProjects();
    }

    // ADD NEW CANCELLATION
    @RequestMapping(path = "/addcancellation")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<Project> addProjectToCancelled(@RequestParam long id, @RequestParam Project cancelledProject) {

        User user = userRepository.findOne(id);
        user.getCancellations().add(cancelledProject);
        LOG.info("Added Projects to List of Cancellations of: " + user.toString());
        return user.getCancellations();
    }

    // GET USERS OF PROJECT
    @RequestMapping(path = "/usersofproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    List<User> getUsersOfProject(@RequestParam long id) {

        Project project = projectRepository.findOne(id);
        LOG.info("Returned all Users of: " + project.toString());
        return project.getUsers();
    }

    // RESERVE SLOTS IN PROJECT
    @RequestMapping(path = "/reserveslotsinproject")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    int reserve(@RequestParam long id, @RequestParam int slots){
        Project project = projectRepository.findOne(id);
        int totalslots = project.getSlots();
        int reservedslots = project.getSlotsReserved();
        int allslots = reservedslots + slots;
        if (reservedslots == 0 && reservedslots <= totalslots)
        {
            project.setSlotsReserved(slots);
            LOG.info("Reserved " + slots + " Slots in " +project.toString());
            return project.getSlotsReserved();
        }
        else if (allslots <= totalslots)
        {
            project.setSlotsReserved(allslots);
            LOG.info("Reserved " + slots + " Slots in " +project.toString());
            return project.getSlotsReserved();
        }
        else {
            LOG.info("Error");
            return project.getSlotsReserved();
        }
    }


    // Expect requests from this origin
    @CrossOrigin(origins = "http://localhost:8090")
    @RequestMapping(path     = "/register",
        method   = RequestMethod.POST,
        consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        })
    public String register(@RequestBody Map<String, Object> request) throws Exception {
        return "Recieved POST request to `/api/register`.";
    }
}
