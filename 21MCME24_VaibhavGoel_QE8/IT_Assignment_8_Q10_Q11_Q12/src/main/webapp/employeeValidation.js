let submit = document.getElementById('submit-btn');
let employeeId = document.getElementById('employeeId');
let name = document.getElementById('name');
let jobTitle = document.getElementById('jobTitle');
let birthDate = document.getElementById('birthDate');
let joinDate = document.getElementById('joinDate');
let salary = document.getElementById('salary');
let departmentId = document.getElementById('departmentId');

submit.addEventListener('click',function(event)
{
     let errors = document.getElementById('errors');
    
    let text = "<ul>"
    if( employeeId.value == '' || name.value == '' || jobTitle.value == '' || birthDate.value == '' || joinDate.value == '' || salary.value == '' || departmentId.value == ''){
        
        if(employeeId.value == ''){
            text += '<li>Employee ID is not entered in the field</li>'
        }
        if(name.value == ''){
            text += '<li>Employee Name is not entered in the field</li>'
        }
        if(jobTitle.value == ''){
            text += '<li>Job Title is not entered in the field</li>'
        }
        if(birthDate.value == ''){
            text += '<li>Birth Date is not entered in the field</li>'
        }
        if(joinDate.value == ''){
            text += '<li>Join Date is not entered in the field</li>'
        }
        if(salary.value == ''){
            text += '<li>Salary is not entered in the field</li>'
        }
        if(departmentId.value == ''){
            text += '<li>Department ID is not entered in the field</li>'
        }

        text += "</ul><br>"
       

        
        errors.innerHTML = text + "<h1 style = 'text-align:center'>DATA BEING ENTERED IS NOT IN CORRECT FORMAT. REJECTED!!</h1>";

        event.preventDefault();
    }

    else{

        let employeeIdCondition = true;
        let NameCondititon = true;
        let jobTitleCondition = true;
        let departmentIdCondition = true;
        
        let y = employeeId.value.toString();
        for(let i=0; i<(y.length); i++){
            if( !( (y.charCodeAt(i)>=48 && y.charCodeAt(i)<=57) ) ){
                employeeIdCondition = false;
                break;
            }
        }

        for(let i=0; i<(name.value.length); i++){
            if( !( (name.value.charCodeAt(i)>=65 && name.value.charCodeAt(i)<=90) || (name.value.charCodeAt(i)>=97 && name.value.charCodeAt(i)<=122) ||(name.value.charCodeAt(i)==32) ) ){
                NameCondititon = false;
                break;
            }
        }


        for(let i=0; i<(jobTitle.value.length); i++){
            if( !( (jobTitle.value.charCodeAt(i)>=65 && jobTitle.value.charCodeAt(i)<=90) || (jobTitle.value.charCodeAt(i)>=97 && jobTitle.value.charCodeAt(i)<=122) || (jobTitle.value.charCodeAt(i)==32) ) ){
                jobTitleCondition = false;
                break;
            }
        }
        
        let z = departmentId.value.toString();
        for(let i=0; i<(z.length); i++){
            if( !( (z.charCodeAt(i)>=48 && z.charCodeAt(i)<=57) ) ){
                departmentIdCondition = false;
                break;
            }
        }



        if( !(employeeIdCondition && NameCondititon && jobTitleCondition && departmentIdCondition) ){

            
            errors.innerHTML = "<h1 style = 'text-align:center'>DATA BEING ENTERED IS NOT IN CORRECT FORMAT. REJECTED!!</h1>";
            
            event.preventDefault();
            
        }
    }
})