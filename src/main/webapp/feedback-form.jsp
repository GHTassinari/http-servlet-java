<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Painel de Testes de Servlets</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 2em; }
    label, select, input, textarea, button { display: block; margin: .5em 0; }
    #extraParams { margin-top: 1em; }
  </style>
</head>
<body>

  <h1>Painel de Testes</h1>

  <form id="testForm" method="get">
    <label for="servletSelect">Escolha o servlet:</label>
    <select id="servletSelect">
      <option value="inspect|get">Inspeção de Cabeçalhos (GET /inspect)</option>
      <option value="echo-body|post">Echo Body (POST /echo-body)</option>
      <option value="welcome|get">Bem-vindo (GET /welcome)</option>
      <option value="set-status|get">Status Dinâmico (GET /set-status)</option>
      <option value="allowed|get">Métodos Suportados (GET /allowed)</option>
    </select>

    <div id="extraParams"></div>

    <button type="submit">Executar</button>
  </form>

  <script>
    const ctx   = '${pageContext.request.contextPath}';
    const form  = document.getElementById('testForm');
    const sel   = document.getElementById('servletSelect');
    const extra = document.getElementById('extraParams');

    function updateForm() {
      const [path, method] = sel.value.split('|');
      form.action = ctx + '/' + path;
      form.method = method.toLowerCase();

      extra.innerHTML = '';

      if (path === 'set-status') {
        const lbl = document.createElement('label');
        lbl.htmlFor = 'statusCode';
        lbl.textContent = 'Código HTTP:';
        const inp = document.createElement('input');
        inp.type = 'number';
        inp.id = 'statusCode';
        inp.name = 'status';
        inp.value = '200';
        inp.required = true;
        extra.appendChild(lbl);
        extra.appendChild(inp);
      }

      if (path === 'echo-body' && method === 'post') {
        const lbl = document.createElement('label');
        lbl.htmlFor = 'postBody';
        lbl.textContent = 'Corpo (raw text):';
        const ta = document.createElement('textarea');
        ta.id = 'postBody';
        ta.name = 'body';
        ta.rows = 5;
        ta.style.width = '100%';
        extra.appendChild(lbl);
        extra.appendChild(ta);
      }
    }

    sel.addEventListener('change', updateForm);
    updateForm();
  </script>

</body>
</html>
