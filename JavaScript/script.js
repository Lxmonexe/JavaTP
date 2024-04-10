function style(){
    const link = document.querySelector("a").setAttribute('href', 'https://esirem.u-bourgogne.fr/');
    const title = document.querySelector("h1").innerText = "Polytech Dijon";
    const paragraph = document.querySelector("strong").style.color = "#4691ff";
}

function testValue(){
    const value = document.querySelector("input").value;
    if(isNaN(value)){
        document.querySelector("input").style.backgroundColor = "red";
    }
    else if (value % 2 === 0){
        document.querySelector("input").style.backgroundColor = "yellow";
    }
    else{
        document.querySelector("input").style.backgroundColor = "blue";
    }
}

function addOption(){
    const select = document.querySelector("select");
    const option = document.createElement("option");
    const valeur = document.getElementById("sel").value;
    option.text = valeur;
    select.add(option);
}

function removeOption(){
    const select = document.querySelector("select");
    select.remove(select.selectedIndex);
}

function connectClickEvent(){
    const button = document.querySelector("button");
    button.addEventListener("click", testValue);
}

function connectClickEventAdd(){
    const button = document.getElementById("add");
    button.addEventListener("click", addOption);
}

function connectClickEventSupp(){
    const button = document.getElementById("supp");
    button.addEventListener("click", removeOption);
}

connectClickEvent();
connectClickEventAdd();
connectClickEventSupp();
style();
