function updateListPage(growlId, tableId, dialogId, param) {
	// console.log("growlId=" + growlId + ", tableId=" + tableId + ", dialogId="
	// + dialogId + ", param.entityChanged=" + param.entityChanged
	// + ", param.messageAfterAction=" + param.messageAfterAction);
	if (param.entityChanged == undefined || !param.entityChanged) {
		atualizaFomulario();
		return;
	}

	addMessage(growlId, param.messageAfterAction);

	PF(dialogId).hide();
	PF(tableId).filter();
}

function addMessage(growlId, message) {
	if (message == undefined)
		return;

	// console.log("message.summary=" + message.summary + ", message.detail="
	// + message.detail + ", message.severity=" + message.severity);
	PF(growlId).renderMessage({
		"summary" : message.summary,
		"detail" : message.detail,
		"severity" : message.severity
	});
}