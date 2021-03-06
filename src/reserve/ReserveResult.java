package reserve;

import client.Flight;
import java.util.HashMap;
import java.util.Map;

/**
 * Reserve result after reserving.
 * <p>
 * After reserving a flight plan, reserver result will be returned. It contains 5 parts, result, allAvailable, allReserved, lockSucceed, unlockSucceed.
 * </p>
 *
 */
public class ReserveResult {

    /**
     * Result is a hash table where key is flight class in a flight plan while the value is if this flight reserved
     */
    private HashMap<Flight, Boolean> result;
    /**
     * If all the seats in plan are available to reserve. If false, no reserving action executed.
     */
    private boolean AllAvailable;
    /**
     * allReserved indicated if all flights are reserved. if it is true then user's seats' reserving are all-set.
     */
    private boolean AllReserved;
    /**
     * lockSucceed indicate if lock has succeeded, if not true it means the other user are reserving.
     */
    private boolean LockSucceed;
    /**
     * unlockSucceed means db has been unlocked, but if it's false, the serve need to unlock by itself unless unlocking again
     */
    private boolean UnlockSucceed;

    /**
     * if each flight reserving in plan succeeded
     *
     * @return hash table key is flight and value is boolean
     */
    public HashMap<Flight, Boolean> getResult() {
        return result;
    }

    /**
     * return if all the flights are reserved successfully
     *
     * @return if is all set return false, check this first.
     */
    public boolean isAllReserved() {
        return AllReserved;
    }

    public boolean isLockSucceed() {
        return LockSucceed;
    }

    public boolean isUnlockSucceed() {
        return UnlockSucceed;
    }

    public boolean isAllAvailable() {
        return AllAvailable;
    }

    /**
     * reserving flight plan is all set or not.
     *
     * @return expect to be true. If not need to check the detailed info.
     */
    public boolean isAllSet() {
        return AllReserved && LockSucceed && UnlockSucceed && AllAvailable;
    }

    public ReserveResult(HashMap<Flight, Boolean> result,
            boolean lockSucceed, boolean allAvailable, boolean allReserved, boolean unlockSucceed) {
        this.result = result;
        LockSucceed = lockSucceed;
        UnlockSucceed = unlockSucceed;
        AllReserved = allReserved;
        AllAvailable = allAvailable;
    }

    public String toString() {
        return "LockDB succeed:" + LockSucceed + " | All available:" + AllAvailable
                + " | all reserved:" + AllReserved + " | Unlocked succeed:"
                + UnlockSucceed + " \n  detailed result:\n    " + result;
    }

    public String getResultString() {

        if (!LockSucceed) {
            return "Error: Failed to establish communication with the server!";
        } else if (!UnlockSucceed) {
            return "Error: Failed to communicate with the server!";
        } else if (!AllAvailable) {
            String errorStr = "Error: The following flights are now unavailable:\n";

            for (Map.Entry<Flight, Boolean> entry : result.entrySet()) {
                if (entry.getValue() == false) {
                    errorStr += "- #" + entry.getKey().getFlightNo() + "\n";
                }
            }

            return errorStr;

        } else if (!AllReserved) {
            String errorStr = "Error: The following flights could not be reserved:\n";

            for (Map.Entry<Flight, Boolean> entry : result.entrySet()) {
                if (entry.getValue() == false) {
                    errorStr += "- #" + entry.getKey().getFlightNo() + "\n";
                }
            }

            return errorStr;

        } else {

            String successStr = "All flights successfully reserved!\n\nFlights:\n";

            for (Map.Entry<Flight, Boolean> entry : result.entrySet()) {

                successStr += "- #" + entry.getKey().getFlightNo() + "\n";

            }

            return successStr;
        }
    }

}
