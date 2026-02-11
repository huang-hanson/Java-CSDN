// DOM Elements
const searchInput = document.getElementById('searchInput');
const categoryFilter = document.getElementById('categoryFilter');
const pricingFilter = document.getElementById('pricingFilter');
const sortFilter = document.getElementById('sortFilter');
const modelCards = document.querySelectorAll('.model-card');

// Search functionality
searchInput.addEventListener('input', filterModels);

// Filter change listeners
categoryFilter.addEventListener('change', filterModels);
pricingFilter.addEventListener('change', filterModels);
sortFilter.addEventListener('change', sortModels);

// Filter models based on search and filters
function filterModels() {
    const searchTerm = searchInput.value.toLowerCase();
    const categoryValue = categoryFilter.value;
    const pricingValue = pricingFilter.value;

    modelCards.forEach(card => {
        const title = card.querySelector('h3').textContent.toLowerCase();
        const company = card.querySelector('.company').textContent.toLowerCase();
        const description = card.querySelector('.description').textContent.toLowerCase();
        const features = Array.from(card.querySelectorAll('.feature')).map(f => f.textContent.toLowerCase()).join(' ');
        
        const cardCategory = card.dataset.category;
        const cardPricing = card.dataset.pricing;

        // Check search match
        const searchMatch = searchTerm === '' || 
            title.includes(searchTerm) || 
            company.includes(searchTerm) || 
            description.includes(searchTerm) ||
            features.includes(searchTerm);

        // Check category match
        const categoryMatch = categoryValue === 'all' || cardCategory === categoryValue;

        // Check pricing match
        const pricingMatch = pricingValue === 'all' || cardPricing === pricingValue;

        // Show or hide card
        if (searchMatch && categoryMatch && pricingMatch) {
            card.classList.remove('hidden');
            // Re-trigger animation
            card.style.animation = 'none';
            card.offsetHeight; // Trigger reflow
            card.style.animation = null;
        } else {
            card.classList.add('hidden');
        }
    });

    // Update count
    updateVisibleCount();
}

// Sort models
function sortModels() {
    const sortValue = sortFilter.value;
    const grid = document.querySelector('.models-grid');
    const visibleCards = Array.from(modelCards).filter(card => !card.classList.contains('hidden'));

    visibleCards.sort((a, b) => {
        switch (sortValue) {
            case 'name':
                return a.querySelector('h3').textContent.localeCompare(b.querySelector('h3').textContent);
            case 'rating':
                return parseFloat(b.dataset.rating) - parseFloat(a.dataset.rating);
            case 'popularity':
                return parseInt(b.dataset.popularity) - parseInt(a.dataset.popularity);
            default:
                return 0;
        }
    });

    // Re-append sorted cards
    visibleCards.forEach((card, index) => {
        card.style.animationDelay = `${index * 0.05}s`;
        grid.appendChild(card);
    });
}

// Update visible count
function updateVisibleCount() {
    const visibleCount = document.querySelectorAll('.model-card:not(.hidden)').length;
    console.log(`Showing ${visibleCount} models`);
}

// Smooth scroll for navigation links
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});

// Add intersection observer for scroll animations
const observerOptions = {
    threshold: 0.1,
    rootMargin: '0px 0px -50px 0px'
};

const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.style.opacity = '1';
            entry.target.style.transform = 'translateY(0)';
        }
    });
}, observerOptions);

// Observe resource cards
document.querySelectorAll('.resource-card').forEach(card => {
    card.style.opacity = '0';
    card.style.transform = 'translateY(20px)';
    card.style.transition = 'opacity 0.5s ease, transform 0.5s ease';
    observer.observe(card);
});

// Add keyboard navigation for search
searchInput.addEventListener('keydown', (e) => {
    if (e.key === 'Escape') {
        searchInput.value = '';
        filterModels();
        searchInput.blur();
    }
});

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    // Initial sort by popularity
    sortModels();
    updateVisibleCount();
    
    // Add loaded class for any initial animations
    document.body.classList.add('loaded');
});

// Console welcome message
console.log('%c🎬 AI Video Models Directory', 'font-size: 24px; font-weight: bold; color: #6366f1;');
console.log('%cDiscover the best AI video generation tools!', 'font-size: 14px; color: #94a3b8;');