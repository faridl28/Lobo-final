const api = '/api/posts';

async function fetchPosts(){
  const res = await fetch(api);
  const posts = await res.json();
  const container = document.getElementById('posts');
  container.innerHTML = '';
  posts.forEach(p=>{
    const el = document.createElement('div');
    el.innerHTML = `<h3>${escapeHtml(p.titulo)}</h3><p>${escapeHtml(p.contenido)}</p><small>${p.autorUsername || ''}</small>`;
    container.appendChild(el);
  });
}

function escapeHtml(s){ return s? s.replaceAll('&','&amp;').replaceAll('<','&lt;').replaceAll('>','&gt;') : ''; }

document.getElementById('postForm').addEventListener('submit', async (e)=>{
  e.preventDefault();
  const titulo = document.getElementById('titulo').value.trim();
  const contenido = document.getElementById('contenido').value.trim();
  if(titulo.length<3 || contenido.length<10){ alert('Revisa los campos'); return; }
  const res = await fetch(api, {
    method:'POST',
    headers:{'Content-Type':'application/json'},
    body: JSON.stringify({titulo, contenido})
  });
  if(res.ok){ document.getElementById('postForm').reset(); fetchPosts(); }
  else { alert('Error al crear post'); }
});

fetchPosts();
