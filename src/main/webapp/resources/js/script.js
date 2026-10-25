function copyText(elementId) {
    const field = document.getElementById(elementId);
    const text = field ? field.value : '';
    if (!text) {
        window.alert('Il n’y a rien à copier.');
        return;
    }
    navigator.clipboard.writeText(text)
        .then(() => window.alert('Texte copié.'))
        .catch(() => window.alert('La copie a échoué.'));
}
