let submit = document.getElementById('submit-btn');
let employeeId = document.getElementById('employeeId');
let name = document.getElementById('name');
let jobTitle = document.getElementById('jobTitle');


submit.addEventListener('click',function(event)
{
     let errors = document.getElementById('errors');
    
    let text = "<ul>"
    if( employeeId.value == '' || name.value == '' || jobTitle.value == '' ){
        
        if(employeeId.value == ''){
            text += '<li>Department ID is not entered in the field</li>'
        }
        if(name.value == ''){
            text += '<li>Department Name is not entered in the field</li>'
        }
        if(jobTitle.value == ''){
            text += '<li>Department Location is not entered in the field</li>'
        }
       
        text += "</ul><br>"
       

        
        errors.innerHTML = text + "<h1 style = 'text-align:center'>DATA BEING ENTERED IS NOT IN CORRECT FORMAT. REJECTED!!</h1>";

        event.preventDefault();
    }

    else{

        let employeeIdCondition = true;
        let NameCondititon = true;
        let jobTitleCondition = true;
        
        let y = employeeId.value.toString();
        for(let i=0; i<(y.length); i++){
            if( !( (y.charCodeAt(i)>=48 && y.charCodeAt(i)<=57) ) ){
                employeeIdCondition = false;
                break;
            }
        }

        for(let i=0; i<(name.value.length); i++){
            if( !( (name.value.charCodeAt(i)>=65 && name.value.charCodeAt(i)<=90) || (name.value.charCodeAt(i)>=97 && name.value.charCodeAt(i)<=122) || (name.value.charCodeAt(i)==32) ) ){
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



        if( !(employeeIdCondition && NameCondititon && jobTitleCondition) ){

            
            errors.innerHTML = "<h1 style = 'text-align:center'>DATA BEING ENTERED IS NOT IN CORRECT FORMAT. REJECTED!!</h1>";
            
            event.preventDefault();
            
        }
    }
})