function removeListeners(el) {
    var nel = el.cloneNode(true);
    el.parentNode.replaceChild(nel, el);
    return nel
}

function setType(el, type) {
    el.setAttribute("type", type)
}

function changeClass(prevCl, newCl) {
    var el = document.getElementsByClassName(prevCl)[0];
    el.className = newCl;
}

function attachResults(gs, results, hooks) {
    results.forEach(function (ar) {
        var r = document.createElement("div");

        var th = document.createElement("div");
        th.className = "gsc-thumbnail-inside";
        var tit = document.createElement("div");
        tit.className = "gs-title";
        var a = document.createElement("a");
        a.className = "gs-title";
        a.dir = "ltr";
        a.href = document.URL.substring(0, document.URL.indexOf("#")) + "\#" + hooks[ar.id];
        a.innerHTML = "<span style='color: cornflowerblue;'>" + ar.title + "</span>";
        a.addEventListener("click", function () {
            closeResults();
        });

        var tab = document.createElement("table");
        tab.className = "gsc-table-result";
        var tb = document.createElement("tbody");
        var tr = document.createElement("tr");
        var tdr = document.createElement("td");
        tdr.className = "gsc-table-cell-snippet-close";
        var tdrt = document.createElement("div");
        tdrt.className = "gs-bidi-start-align gs-snippet";
        tdrt.dir = "ltr";
        tdrt.innerText = "hook: " + hooks[ar.id] + ", id: " + ar.id + ", title: " + ar.title;

        tit.appendChild(a);
        th.appendChild(tit);

        tdr.appendChild(tdrt);
        tr.appendChild(tdr);
        tb.appendChild(tr);
        tab.appendChild(tb);

        r.appendChild(th);
        r.appendChild(tab);
        gs.appendChild(r);
    });
}

function attachError(gs, error) {
    var c = document.createElement("div");
    c.className = "gs-bidi-start-align gs-snippe";
    c.dir = "ltr";
    c.innerText = error;

    gs.appendChild(c);
}

function expandArea() {
    var end = document.getElementsByClassName("gsc-expansionArea")[0];
    var root = end.parentElement;
    root.className = "gsc-results gsc-webResult";
    root.style.display = "block";

    var gs = document.createElement("div");
    gs.className = "gsc-webResult gsc-result";

    root.insertBefore(gs, end);

    return gs;
}

function insertResults(results, hooks) {
    var gs = expandArea();
    attachResults(gs, results, hooks);
}

function insertError(error) {
    var gs = expandArea();
    attachError(gs, error);
}

function showResults(results, hooks) {
    changeClass("gsc-results-wrapper-overlay", "gsc-results-wrapper-overlay gsc-results-wrapper-visible");
    changeClass("gsc-results-close-btn", "gsc-results-close-btn gsc-results-close-btn-visible");
    changeClass("gsc-resultsbox-invisible", "gsc-resultsbox-visible");
    insertResults(results, hooks);
}

function showError(error) {
    insertError(error)
}

function clearResults() {
    var e = document.getElementsByClassName("gsc-expansionArea")[0].parentElement;
    if (e.childElementCount > 1) {
        e.removeChild(e.firstChild);
    }
}

function closeResults() {
    clearResults();
    changeClass("gsc-resultsbox-visible", "gsc-resultsbox-invisible");
    changeClass("gsc-results-close-btn gsc-results-close-btn-visible", "gsc-results-close-btn");
    changeClass("gsc-results-wrapper-overlay gsc-results-wrapper-visible", "gsc-results-wrapper-overlay");
}

function buildHooks() {
    var hooks = {};
    var els = document.getElementsByClassName("t106__title t-title t-title_xxs");
    Array.prototype.forEach.call(els, function (el) {
        var href = el.parentElement.parentElement.parentElement.parentElement.parentElement.id;
        var child = el.firstElementChild;
        var i = child.innerHTML.indexOf("id");
        if (i >= 0) {
            var id = child.innerHTML.substr(i + 3);
            child.innerHTML = child.innerHTML.substring(0, i);
            hooks[id] = href;
        }
    });

    return hooks;
}

window.addEventListener("load", function () {
    var hooks = buildHooks();

    var i = document.getElementsByClassName("gsc-input-box")[0];
    removeListeners(i);

    var b = document.getElementsByClassName("gsc-search-button gsc-search-button-v2")[0];
    b = removeListeners(b);
    setType(b, "button");
    b.addEventListener("click", function () {
        var inp = document.getElementsByTagName("input")[0];
        if (inp && inp.value) {
            var xhr = new XMLHttpRequest();
            var qparam = "q=" + encodeURIComponent(inp.value);
            xhr.open("GET", "http://localhost:8093/api/search?" + qparam, true);
            xhr.send();
            xhr.onreadystatechange = function () {
                if (xhr.readyState !== 4) return;
                if (xhr.status === 200) {
                    showResults(JSON.parse(xhr.responseText), hooks);
                } else {
                    showError("status: " + xhr.status + ", text: " + xhr.responseText)
                }
            }
        }
    });

    var cb = document.getElementsByClassName("gsc-results-close-btn")[0];
    cb = removeListeners(cb);
    cb.addEventListener("click", function () {
        closeResults();
    });
});

