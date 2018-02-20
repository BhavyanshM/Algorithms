import sys
import clr
import time
import MissionPlanner #import *

# includes the Utilities class
clr.AddReference("MissionPlanner.Utilities") 

print("Starting Mission")

# changes mode to "Guided"
Script.ChangeMode("Guided")                     

print('Guided Mode')

# creating waypoint
item = MissionPlanner.Utilities.Locationwp() 

# Create the First Waypoint

lat = -35.3667082
lng = 149.1653681
alt = 200

MissionPlanner.Utilities.Locationwp.lat.SetValue(item,lat)     # sets latitude
MissionPlanner.Utilities.Locationwp.lng.SetValue(item,lng)   # sets longitude
MissionPlanner.Utilities.Locationwp.alt.SetValue(item,alt)     # sets altitude
print 'WP 1 set'

# tells UAV "go to" the set lat/long @ alt
MAV.setGuidedModeWP(item)                                    
# print 'Going to WP 1'
# time.sleep(10)                                                            # wait 10 seconds
# print 'Ready for next WP'
# lat = 39.345358
# lng = -86.029054
# alt = 76.199999
# MissionPlanner.Utilities.Locationwp.lat.SetValue(item,lat)
# MissionPlanner.Utilities.Locationwp.lng.SetValue(item,lng)
# MissionPlanner.Utilities.Locationwp.alt.SetValue(item,alt)
# print 'WP 2 set'
# MAV.setGuidedModeWP(item)
# print 'Going to WP 2'
# time.sleep(10)
# print 'Ready for next WP'
# lat = 39.342106
# lng = -86.031371
# alt = 53.340000
# MissionPlanner.Utilities.Locationwp.lat.SetValue(item,lat)
# MissionPlanner.Utilities.Locationwp.lng.SetValue(item,lng)
# MissionPlanner.Utilities.Locationwp.alt.SetValue(item,alt)
# print 'WP 3 set'
# MAV.setGuidedModeWP(item)
# print 'Going to WP 3'
# time.sleep(10)
# print 'Ready for next WP'
# lat = -35.26
# lng = 149.16
# alt = 153.199999
# MissionPlanner.Utilities.Locationwp.lat.SetValue(item,lat)
# MissionPlanner.Utilities.Locationwp.lng.SetValue(item,lng)
# MissionPlanner.Utilities.Locationwp.alt.SetValue(item,alt)
# print 'WP 4 set'
# MAV.setGuidedModeWP(item)
# print 'Going to WP 4'
# time.sleep(10)
# print 'Mission Complete'
# #MAV.setMode(RETURN_TO_LAUNCH)
# Script.ChangeMode("RTL")                                      # Return to Launch point
# print 'Returning to Launch'
# time.sleep(10)
# Script.ChangeMode("LOITER")                                # switch to "LOITER" mode
# print 'LOITERING'