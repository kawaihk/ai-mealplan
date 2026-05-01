import { API_CONFIG } from '../constants.js';

export const apiClient = {
    async request(endpoint, options = {}) {
        const url = `${API_CONFIG.BASE_URL}${endpoint}`;
        const defaultHeaders = {
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        };

        try {
            const response = await fetch(url, {
                ...options,
                headers: { ...defaultHeaders, ...options.headers }
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || `HTTP error! status: ${response.status}`);
            }

            return response.status !== 204 ? await response.json() : null;
        } catch (error) {
            console.error('API Request Error:', error);
            throw error;
        }
    },
    get(path) { return this.request(path, { method: 'GET' }); },
    post(path, data) { return this.request(path, { method: 'POST', body: JSON.stringify(data) }); },
    put(path, data) { return this.request(path, { method: 'PUT', body: JSON.stringify(data) }); }
};