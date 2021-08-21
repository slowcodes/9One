package com.shashank.platform.classroomappui.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.api.ApiClient;
import com.shashank.platform.classroomappui.api.ApiInterface;
import com.shashank.platform.classroomappui.base.BaseActivity;
import com.shashank.platform.classroomappui.models.SignUpResponse;
import com.shashank.platform.classroomappui.util.PearlTextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RevalidationActivity extends BaseActivity implements View.OnClickListener {

    private TextInputEditText edt_fname;
    private TextInputEditText edt_lname;
    private TextInputEditText edt_middle_name;
    private TextInputEditText edt_madien_name;
    private AutoCompleteTextView act_sex;
    private AutoCompleteTextView act_religion;
    private TextInputEditText edt_dob;
    private TextInputEditText edt_home_town;
    private AutoCompleteTextView act_state_origin;
    private AutoCompleteTextView act_gov_area;
    private TextInputEditText edt_email;
    private TextInputEditText edt_tel;
    private TextInputEditText edt_address;
    private TextInputEditText edt_city;
    private AutoCompleteTextView act_state;
    private AutoCompleteTextView act_edu_gov_body;
    private AutoCompleteTextView act_category;
    private AutoCompleteTextView act_institution;
    private AutoCompleteTextView act_faculty_school;
    private AutoCompleteTextView act_course_department;
    private AutoCompleteTextView act_academic_session;
    private AutoCompleteTextView act_current_academic_level;
    private AutoCompleteTextView act_matric;
    private TextInputEditText edt_next_of_kin;
    private TextInputEditText edt_kin_address;
    private TextInputEditText edt_kin_phone;
    private TextInputEditText edt_kin_email;
    private Button btn_revalidate;

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revalidation);

        findViews();
        initViews();
        setListeners();
    }

    private void findViews() {
        edt_fname = findViewById(R.id.edt_fname);
        edt_lname = findViewById(R.id.edt_lname);
        edt_middle_name = findViewById(R.id.edt_middle_name);
        edt_madien_name = findViewById(R.id.edt_madien_name);
        act_sex = findViewById(R.id.act_sex);
        act_religion = findViewById(R.id.act_religion);
        edt_dob = findViewById(R.id.edt_dob);
        edt_home_town = findViewById(R.id.edt_home_town);
        act_state_origin = findViewById(R.id.act_state_origin);
        act_gov_area = findViewById(R.id.act_gov_area);
        edt_email = findViewById(R.id.edt_email);
        edt_tel = findViewById(R.id.edt_tel);
        edt_address = findViewById(R.id.edt_address);
        edt_city = findViewById(R.id.edt_city);
        act_state = findViewById(R.id.act_state);
        act_edu_gov_body = findViewById(R.id.act_edu_gov_body);
        act_category = findViewById(R.id.act_category);
        act_institution = findViewById(R.id.act_institution);
        act_faculty_school = findViewById(R.id.act_faculty_school);
        act_course_department = findViewById(R.id.act_course_department);
        act_academic_session = findViewById(R.id.act_academic_session);
        act_current_academic_level = findViewById(R.id.act_current_academic_level);
        act_matric = findViewById(R.id.act_matric);
        edt_next_of_kin = findViewById(R.id.edt_next_of_kin);
        edt_kin_address = findViewById(R.id.edt_kin_address);
        edt_kin_phone = findViewById(R.id.edt_kin_phone);
        edt_kin_email = findViewById(R.id.edt_kin_email);
        btn_revalidate = findViewById(R.id.btn_revalidate);
    }

    @Override
    public void initViews() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spSexAdapter = ArrayAdapter.createFromResource(this, R.array.sex, R.layout.dropdown_menu_popup_item);
        // Specify the layout to use when the list of choices appears
        spSexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        act_sex.setAdapter(spSexAdapter);
        //act_sex.setText("SPT", false);

        ArrayAdapter<CharSequence> spreligionAdapter = ArrayAdapter.createFromResource(this, R.array.religion, R.layout.dropdown_menu_popup_item);
        spreligionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        act_religion.setAdapter(spreligionAdapter);

        ArrayAdapter<CharSequence> spLocalGovAdapter = ArrayAdapter.createFromResource(this, R.array.local_govenment_area, R.layout.dropdown_menu_popup_item);
        spLocalGovAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        act_gov_area.setAdapter(spLocalGovAdapter);

        ArrayAdapter<CharSequence> spEduGovBodyAdapter = ArrayAdapter.createFromResource(this, R.array.education_governing_body, R.layout.dropdown_menu_popup_item);
        spEduGovBodyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        act_edu_gov_body.setAdapter(spEduGovBodyAdapter);

        ArrayAdapter<CharSequence> spCategoryAdapter = ArrayAdapter.createFromResource(this, R.array.category, R.layout.dropdown_menu_popup_item);
        spCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        act_category.setAdapter(spCategoryAdapter);

        ArrayAdapter<CharSequence> spAcSessionAdapter = ArrayAdapter.createFromResource(this, R.array.academic_session, R.layout.dropdown_menu_popup_item);
        spAcSessionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        act_academic_session.setAdapter(spAcSessionAdapter);

        ArrayAdapter<CharSequence> spAclevelAdapter = ArrayAdapter.createFromResource(this, R.array.academic_level, R.layout.dropdown_menu_popup_item);
        spAclevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        act_current_academic_level.setAdapter(spAclevelAdapter);
    }

    @Override
    public void setListeners() {
        btn_revalidate.setOnClickListener(this);
        edt_dob.setOnClickListener(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_revalidate:
                if (checkValidation()) {
                    signUp();
                }
                break;
            case R.id.tv_sign_in:
                break;
            case R.id.edt_dob:
                showDatePickerDialog();
                break;
        }
    }

    private boolean checkValidation() {
        if (PearlTextUtils.isBlank(edt_fname.getText().toString().trim())) {
            showToastMsg("Please enter First name");
            return false;
        } else if (PearlTextUtils.isBlank(edt_lname.getText().toString().trim())) {
            showToastMsg("Please enter Last name");
            return false;
        } else if (PearlTextUtils.isBlank(act_sex.getText().toString().trim())) {
            showToastMsg("Please select sex");
            return false;
        } else if (PearlTextUtils.isBlank(act_religion.getText().toString().trim())) {
            showToastMsg("Please select religion");
            return false;
        } else if (PearlTextUtils.isBlank(edt_dob.getText().toString().trim())) {
            showToastMsg("Please select date of birth");
            return false;
        } else if (PearlTextUtils.isBlank(edt_home_town.getText().toString().trim())) {
            showToastMsg("Please enter home town");
            return false;
        } else if (PearlTextUtils.isBlank(act_gov_area.getText().toString().trim())) {
            showToastMsg("Please select government Area");
            return false;
        } else if (PearlTextUtils.isBlank(edt_email.getText().toString().trim())) {
            showToastMsg("Please enter email");
            return false;
        } else if (PearlTextUtils.isBlank(edt_tel.getText().toString().trim())) {
            showToastMsg("Please enter phone");
            return false;
        } else if (PearlTextUtils.isBlank(edt_address.getText().toString().trim())) {
            showToastMsg("Please enter Street address");
            return false;
        } else if (PearlTextUtils.isBlank(edt_city.getText().toString().trim())) {
            showToastMsg("Please enter city");
            return false;
        } else if (PearlTextUtils.isBlank(act_edu_gov_body.getText().toString().trim())) {
            showToastMsg("Please select government body");
            return false;
        } else if (PearlTextUtils.isBlank(act_category.getText().toString().trim())) {
            showToastMsg("Please select category");
            return false;
        } else if (PearlTextUtils.isBlank(act_institution.getText().toString().trim())) {
            showToastMsg("Please select Institution");
            return false;
        } else if (PearlTextUtils.isBlank(act_faculty_school.getText().toString().trim())) {
            showToastMsg("Please select Faculty/School");
            return false;
        } else if (PearlTextUtils.isBlank(act_course_department.getText().toString().trim())) {
            showToastMsg("Please select Course/Department");
            return false;
        } else if (PearlTextUtils.isBlank(act_academic_session.getText().toString().trim())) {
            showToastMsg("Please select Academic Session");
            return false;
        } else if (PearlTextUtils.isBlank(act_current_academic_level.getText().toString().trim())) {
            showToastMsg("Please select Current Academic Level");
            return false;
        } else if (PearlTextUtils.isBlank(act_matric.getText().toString().trim())) {
            showToastMsg("Please select Matric");
            return false;
        } else if (PearlTextUtils.isBlank(edt_next_of_kin.getText().toString().trim())) {
            showToastMsg("Please enter Next of Kin");
            return false;
        } else if (PearlTextUtils.isBlank(edt_kin_address.getText().toString().trim())) {
            showToastMsg("Please enter Address of Next of Kin");
            return false;
        } else if (PearlTextUtils.isBlank(edt_kin_phone.getText().toString().trim())) {
            showToastMsg("Please enter Next of Kin Phone #");
            return false;
        } else if (PearlTextUtils.isBlank(edt_kin_email.getText().toString().trim())) {
            showToastMsg("Please enter Next of Kin Email");
            return false;
        }
        return true;
    }

    private void signUp() {
        Map<String, String> map = new HashMap<>();
        map.put("first_name", edt_fname.getText().toString().trim());
        if (!PearlTextUtils.isBlank(edt_middle_name.getText().toString().trim())) {
            map.put("middle_name", edt_middle_name.getText().toString().trim());
        }
        if (!PearlTextUtils.isBlank(edt_madien_name.getText().toString().trim())) {
            map.put("madien_name", edt_middle_name.getText().toString().trim());
        }
        map.put("sex", act_sex.getText().toString().trim());
        map.put("religion", act_religion.getText().toString().trim());
        map.put("dob", edt_dob.getText().toString().trim());
        map.put("home_town", edt_home_town.getText().toString().trim());
        //map.put("", act_state_origin.getText().toString().trim());
        //map.put("", act_gov_area.getText().toString().trim());
        map.put("email", edt_email.getText().toString().trim());
        map.put("phone", edt_tel.getText().toString().trim());
        map.put("address", edt_address.getText().toString().trim());
        //map.put("", edt_city.getText().toString().trim());
        map.put("state_id", act_state.getText().toString().trim());
        //map.put("", act_edu_gov_body.getText().toString().trim());
        //map.put("", act_category.getText().toString().trim());
        //map.put("", act_institution.getText().toString().trim());
        //map.put("", act_faculty_school.getText().toString().trim());
        //map.put("", act_course_department.getText().toString().trim());
        //map.put("", act_academic_session.getText().toString().trim());
        map.put("matric_no", act_matric.getText().toString().trim());
        map.put("nok_name", edt_next_of_kin.getText().toString().trim());
        map.put("nok_address", edt_kin_address.getText().toString().trim());
        map.put("nok_phone", edt_kin_phone.getText().toString().trim());
        map.put("nok_email", edt_kin_email.getText().toString().trim());

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SignUpResponse> call = apiService.signUp(map);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.body() != null) {
                    if (!PearlTextUtils.isBlank(response.body().getSsn())) {
                        showToastMsg(response.body().getMessage());
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                showToastMsg("" + t.getMessage());
                Log.e("TAG", "Response = " + t.toString());
            }
        });
    }

    public void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(year, monthOfYear, dayOfMonth);
            edt_dob.setText(dateFormatter.format(calendar.getTime()));

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        //datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }
}
