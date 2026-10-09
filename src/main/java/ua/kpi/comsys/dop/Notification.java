package ua.kpi.comsys.dop;

/**
 * Sealed hierarchy of notifications (data only, no behaviour).
 */
public sealed interface Notification permits TextMsg, ImageMsg, AudioMsg {
}
