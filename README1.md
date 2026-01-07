
🤖 Gemini API (Special Case – Different Base URL)
When base URL is different, create another Retrofit. DI


| Library                    | Status                     |
| -------------------------- | -------------------------- |
| `material` (Material 2)    | ❌ Old / legacy             |
| `material3` (Material You) | ✅ **Latest & recommended** |


❗ Important rule (remember forever)

Preview + Hilt = ❌ NEVER together
--->You want Preview to work AND Hilt ViewModel to work at runtime.
To achieve this, you must follow the standard Compose architecture pattern used in real projects.


STEP 1: Create a UI-ONLY composable (Preview safe)
STEP 2: Keep ViewModel ONLY in real screen
STEP 3: Preview ONLY the UI composable ✅




✅ Final Answer

👉 Preview will NOT auto update on save
👉 You must refresh manually
👉 This is expected Compose behavior

