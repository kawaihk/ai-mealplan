const API_BASE = '/api/recipes';

document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('recipeForm');
    const recipeTableBody = document.getElementById('recipeTableBody');
    const searchBtn = document.getElementById('searchBtn');
    const cancelBtn = document.getElementById('cancelBtn');

    // 初回読み込み
    loadRecipes();

    // 登録・更新イベント
    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        clearErrors();

        const id = document.getElementById('recipeId').value;
        const recipeData = {
            title: document.getElementById('title').value,
            description: document.getElementById('description').value,
            calories: parseInt(document.getElementById('calories').value) || 0
        };

        const method = id ? 'PUT' : 'POST';
        const url = id ? `${API_BASE}/${id}` : API_BASE;

        try {
            const response = await fetch(url, {
                method: method,
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(recipeData)
            });

            if (response.ok) {
                resetForm();
                loadRecipes();
            } else {
                const errorData = await response.json();
                handleApiError(errorData);
            }
        } catch (error) {
            alert('通信エラーが発生しました');
        }
    });

    // 検索イベント
    searchBtn.addEventListener('click', async () => {
        const keyword = document.getElementById('searchKeyword').value;
        if (!keyword) return loadRecipes();

        const response = await fetch(`${API_BASE}/search?keyword=${encodeURIComponent(keyword)}`);
        const recipes = await response.json();
        renderTable(recipes);
    });

    // キャンセルボタン
    cancelBtn.addEventListener('click', resetForm);

    async function loadRecipes() {
        const response = await fetch(API_BASE);
        const recipes = await response.json();
        renderTable(recipes);
    }

    function renderTable(recipes) {
        recipeTableBody.innerHTML = '';
        recipes.forEach(recipe => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${escapeHtml(recipe.title)}</td>
                <td>${recipe.calories} kcal</td>
                <td>
                    <button onclick="editRecipe(${recipe.id})">編集</button>
                    <button onclick="deleteRecipe(${recipe.id})">削除</button>
                </td>
            `;
            recipeTableBody.appendChild(tr);
        });
    }

    function handleApiError(errorData) {
        if (errorData.details) {
            errorData.details.forEach(d => {
                const errorEl = document.getElementById(`error-${d.field}`);
                if (errorEl) errorEl.textContent = d.message;
            });
        }
        alert(`エラー: ${errorData.message}`);
    }

    function clearErrors() {
        document.querySelectorAll('.error').forEach(el => el.textContent = '');
    }

    function resetForm() {
        form.reset();
        document.getElementById('recipeId').value = '';
        document.getElementById('formTitle').textContent = '新しいレシピを登録';
        document.getElementById('submitBtn').textContent = '登録';
        cancelBtn.style.display = 'none';
        clearErrors();
    }

    // グローバルに公開（onclickイベント用）
    window.editRecipe = async (id) => {
        const response = await fetch(`${API_BASE}/${id}`);
        const recipe = await response.json();
        
        document.getElementById('recipeId').value = recipe.id;
        document.getElementById('title').value = recipe.title;
        document.getElementById('description').value = recipe.description || '';
        document.getElementById('calories').value = recipe.calories || 0;
        
        document.getElementById('formTitle').textContent = 'レシピを編集';
        document.getElementById('submitBtn').textContent = '更新';
        cancelBtn.style.display = 'inline';
        window.scrollTo(0, 0);
    };

    window.deleteRecipe = async (id) => {
        if (!confirm('削除してもよろしいですか？')) return;
        await fetch(`${API_BASE}/${id}`, { method: 'DELETE' });
        loadRecipes();
    };

    function escapeHtml(str) {
        const div = document.createElement('div');
        div.textContent = str;
        return div.innerHTML;
    }
});