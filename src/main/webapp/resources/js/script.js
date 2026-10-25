function copyConversation(){
    const conversation=document.getElementById('conversation');
    const content=conversation?conversation.innerText.trim():'';
    if(!content){window.alert('La conversation est vide.');return;}
    navigator.clipboard.writeText(content).then(()=>window.alert('Conversation copiée.')).catch(()=>window.alert('Impossible de copier la conversation.'));
}
